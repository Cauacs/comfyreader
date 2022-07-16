package xyz.comfyreader.services;


import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.comfyreader.models.RegistrationRequest;
import xyz.comfyreader.models.User;
import xyz.comfyreader.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(RegistrationRequest request) throws  IllegalStateException{
        boolean userExists = userRepository.findByEmail(request.email()).isPresent();
        if (userExists)
            throw new IllegalStateException("Email already used");
        else {
            String encodedPassword = passwordEncoder.encode(request.password());
            userRepository.save(new User(
                    null,
                    request.firstName(),
                    request.lastName(),
                    request.email(),
                    encodedPassword,
                    LocalDateTime.now(),
                    new ArrayList<>()
            ));
        }
    }
    public List<User> findAll() {
        return userRepository.findAll();
    }
    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email not found"));
    }

    public void addUrl(String url) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> optionalUser = userRepository.findByEmail(authentication.getName());
        optionalUser.ifPresentOrElse(
                user -> {
//                    Query query = new Query();
//                    query.addCriteria(Criteria.where("email").is(user.email()));
//                    List<String> urls = user.urls();
//                    Update update = new Update();
//                    update.set("urls", urls.add(url));
                    List<String> urls = user.urls();
                    urls.add(url);
                    User updatedUser = new User(
                            user._id(),
                            user.firstName(),
                            user.lastName(),
                            user.email(),
                            user.password(),
                            user.created(),
                            urls
                    );
                    userRepository.save(updatedUser);
                },
                () -> {throw new IllegalStateException("User not found");}
        );
    }
}
