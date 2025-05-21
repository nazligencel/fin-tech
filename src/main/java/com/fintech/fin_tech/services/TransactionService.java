package com.fintech.fin_tech.services;

import com.fintech.fin_tech.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TransactionService {

    Transaction addTransaction(Transaction transaction);

    List<Transaction> getAllTransactions();

    Optional<Transaction> getTransactionById(Long id);

    Transaction updateTransaction(Long id, Transaction transactionDetails);

    void deleteTransaction(Long id);
}
