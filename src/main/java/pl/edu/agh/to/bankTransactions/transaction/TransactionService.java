package pl.edu.agh.to.bankTransactions.transaction;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }

    List<Transaction> getTransactions(){
        return transactionRepository.findAll();
    }

    Optional<Transaction> getTransaction(int id){
        return transactionRepository.findById(id);
    }

    public void addNewTransactions(List<Transaction> transactions) {
        transactionRepository.saveAll(transactions);
    }

    public void updateTransaction(Transaction transaction){
        this.transactionRepository.save(transaction);
    }

    public List<Transaction> filterTransactionsByTags(List<String> tagNames){
        return transactionRepository.findByTagNames(tagNames);
    }
}