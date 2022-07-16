package xyz.comfyreader.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import xyz.comfyreader.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String>{
    @Override
    Optional<User> findById(String s);
    Optional<User> findByEmail(String email);
}
