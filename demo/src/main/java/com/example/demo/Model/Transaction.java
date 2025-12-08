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

@Document(collection = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {
    @Id
    private String id;                     // MongoDB primary key (_id)

    @Indexed
    private String fromAccountId;          // Account.id (sender)

    @Indexed
    private String toAccountId;            // Account.id (receiver)

    private BigDecimal amount;             // Transaction amount

    private TransactionType type;          // INTERNAL_TRANSFER, EXTERNAL_TRANSFER, etc.

    private TransactionStatus status;      // PENDING, SUCCESS, FAILED

    private String description;            // Any message (optional)

    @CreatedDate
    private Instant createdAt;

}
