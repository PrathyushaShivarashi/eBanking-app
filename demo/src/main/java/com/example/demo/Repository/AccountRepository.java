package com.example.demo.Repository;

import com.example.demo.Model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends MongoRepository<Account,String> {
    //fetch all accounts related to that user
    List<Account> findByUserId(String userId);
    Optional<Account> findByAccountNumber(String accountNumber);




}
