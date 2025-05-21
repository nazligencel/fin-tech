package com.fintech.fin_tech.repositories.impl;

import com.fintech.fin_tech.model.Transaction;
import com.fintech.fin_tech.repositories.TransactionRepository;
import com.fintech.fin_tech.services.TransactionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
    @Override
    @Transactional // Veritabanı işlemlerinin transactional olmasını sağlar
    public Transaction addTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    @Override
    @Transactional
    public Transaction updateTransaction(Long id, Transaction transactionDetails) {
        // Önce güncellenecek transaction'ı bul
        Transaction existingTransaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found with id:" +id));
        // Gelen transactionDetails ile mevcut transaction'ı güncelle
        existingTransaction.setType(transactionDetails.getType());
        existingTransaction.setAmount(transactionDetails.getAmount());
        existingTransaction.setDescription(transactionDetails.getDescription());
        existingTransaction.setTransactionDate(transactionDetails.getTransactionDate());
        existingTransaction.setCategory(existingTransaction.getCategory());

        return transactionRepository.save(existingTransaction);
    }

    @Override
    public void deleteTransaction(Long id) {
        if (!transactionRepository.existsById(id)) {
            throw new RuntimeException("Transaction not found with id:" + id);
        }
        transactionRepository.deleteById(id);
    }
}
