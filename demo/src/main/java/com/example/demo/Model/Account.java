package com.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;
@Document(collection ="accounts")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Account {

    @Id
    private String id;

    @Indexed
    private String userId;

    @Indexed(unique = true)
    private String accountNumber;

    private AccountType type;                  // SAVINGS, CHECKING, CREDIT, LOAN

    private BigDecimal balance;                // always BigDecimal for money

    private String currency;                   // "USD", "INR", etc.

    private AccountStatus status;              // ACTIVE, BLOCKED, CLOSED

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;


}
