package com.example.demo.helperclass;



import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private final UserRepository userRepository;

    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();
    if(userRepository.count() == 0){
        User user = User.builder()
                .firstName("Prathyusha")
                .lastName("shiva")
                .email("prathyusha130@gmail.com")
                .password("1233tydjk")
                .role("user")
                .createdAT(Instant.now())
                .updatedAT(Instant.now())
                .build();
     userRepository.save(user);
        System.out.println("User Inserted successfully");
    }
    else{
        System.out.println("user exists already");
    }
        userRepository.findAll().forEach(System.out::println);

    }
}
