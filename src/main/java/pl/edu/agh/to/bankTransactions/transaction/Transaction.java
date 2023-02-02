package pl.edu.agh.to.bankTransactions.transaction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Transaction {
    @Id
    @GeneratedValue
    private int id;
    private String accountNumber;
    private String correspondent;
    private String date;
    private String transactionType;
    private String amount;
    private String description;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "transaction_tags",
            joinColumns = @JoinColumn(name = "transaction_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags = new HashSet<>();

    public Transaction(String accountNumber, String date, String transactionType,
                       String correspondent, String description, String amount) {
        this.accountNumber = accountNumber;
        this.date = date;
        this.transactionType = transactionType;
        this.correspondent = correspondent;
        this.description = description;
        this.amount = amount;
    }

    public Transaction() {
    }

    @Override
    public String toString() {
        return accountNumber + correspondent + date + transactionType  + amount + description ;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getCorrespondent() {
        return correspondent;
    }

    public String getDate() {
        return date;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public String getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setCorrespondent(String correspondent) {
        this.correspondent = correspondent;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
