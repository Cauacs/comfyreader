package xyz.comfyreader.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Document(collection = "users")
public record User (
        String _id,
        String firstName,
        String lastName,
        String email,
        String password,
        LocalDateTime created,
        List<String> urls
) implements UserDetails {

//    public User(String _id, String firstName, String lastName, String email, String password, LocalDateTime created, List<String> urls) {
//        this._id = _id;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.password = password;
//        this.created = created;
//        this.urls = urls;
//    }
//
//    public User(String firstName, String lastName, String email, String password) {
//        this("", firstName, lastName, email, password, LocalDateTime.now(), new ArrayList<>());
//    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
