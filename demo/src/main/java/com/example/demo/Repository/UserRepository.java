package com.example.demo.Repository;


import com.example.demo.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    // Used for login
    Optional<User> findByEmail(String email);

    // Used for registration (to avoid duplicate emails)
    boolean existsByEmail(String email);
}
