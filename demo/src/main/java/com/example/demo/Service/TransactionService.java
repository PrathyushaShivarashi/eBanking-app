package com.example.demo.Service;

import com.example.demo.DTO.TransferRequest;
import com.example.demo.Model.*;
import com.example.demo.Repository.AccountRepository;
import com.example.demo.Repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Transactional
    public Transaction transfer(TransferRequest transferRequest){

        // Basic request Validation
       if((transferRequest.getFromAccountNumber() == null)  || (transferRequest.getFromAccountNumber().isBlank())){
           throw new IllegalArgumentException("From Account number is required");
       }

        if((transferRequest.getToAccountNumber() == null)  || (transferRequest.getToAccountNumber().isBlank())){
            throw new IllegalArgumentException("To Account number is required");
        }
        if(transferRequest.getToAccountNumber() == transferRequest.getToAccountNumber()){
            throw new IllegalArgumentException("fromAccount number and ToAccount must be different");
        }
        if((transferRequest.getAmount() == null) || (transferRequest.getAmount().compareTo(BigDecimal.ZERO)==0)){
            throw new IllegalArgumentException("Amount must be greater than zero");
        }

        //Load accounts from DB
        Account fromAccount = accountRepository.findByAccountNumber(transferRequest.getFromAccountNumber())
                .orElseThrow(() -> new IllegalArgumentException("from account not found : "
                        +transferRequest.getFromAccountNumber()));

        Account toAccount = accountRepository.findByAccountNumber(transferRequest.getToAccountNumber())
                .orElseThrow(() -> new IllegalArgumentException("To account not found : "
                        +transferRequest.getToAccountNumber()));

        if (fromAccount.getStatus() != AccountStatus.ACTIVE) {
            throw new IllegalStateException("From account is not active");
        }

        if (toAccount.getStatus() != AccountStatus.ACTIVE) {
            throw new IllegalStateException("To account is not active");
        }

        if (!fromAccount.getCurrency().equals(toAccount.getCurrency())) {
            throw new IllegalStateException("Currency mismatch between accounts");
        }

        BigDecimal amount = transferRequest.getAmount();
        if(fromAccount.getBalance().compareTo(amount) < 0){
            //There is no enough balance in the fromAccount so,
            // we are cancelling this transaction

            Transaction failedTransaction = Transaction.builder()
                    .fromAccountId(fromAccount.getId())
                    .toAccountId(toAccount.getId())
                    .amount(amount)
                    .type(TransactionType.INTERNAL_TRANSFER)
                    .status(TransactionStatus.FAILED)
                    .description("Insufficient funds")
                    .build();
            return transactionRepository.save(failedTransaction);
        }

        // Perform transfer , update balances
        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        toAccount.setBalance(toAccount.getBalance().add(amount));

        Transaction successTransaction = Transaction.builder()
                .fromAccountId(fromAccount.getId())
                .toAccountId(toAccount.getId())
                .amount(amount)
                .type(TransactionType.INTERNAL_TRANSFER)
                .status(TransactionStatus.SUCCESS)
                .description(transferRequest.getDescription())
                .build();
        return transactionRepository.save(successTransaction);



    }
}
