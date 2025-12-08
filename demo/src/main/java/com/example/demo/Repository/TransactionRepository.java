package com.example.demo.Repository;

import com.example.demo.Model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
    // Get all transactions where this account is involved (either as sender or receiver)
    List<Transaction> findByFromAccountIdOrToAccountId(String fromAccountId, String toAccountId);
}

