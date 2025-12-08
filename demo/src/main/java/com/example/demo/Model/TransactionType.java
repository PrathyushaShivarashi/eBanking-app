package com.example.demo.Model;

public enum TransactionType {
    INTERNAL_TRANSFER,   // Between two accounts inside your bank
    EXTERNAL_TRANSFER,   // Sending to another bank (future)
    DEPOSIT,             // Optional
    WITHDRAWAL
}
