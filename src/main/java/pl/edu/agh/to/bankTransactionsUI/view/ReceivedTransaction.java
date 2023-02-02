package pl.edu.agh.to.bankTransactionsUI.view;

import java.util.Set;

public class ReceivedTransaction {

    private int id;
    private String accountNumber;
    private String correspondent;
    private String date;
    private String transactionType;
    private String amount;
    private String description;
    private Set<Tag> tags;

    public ReceivedTransaction(int id, String accountNumber, String date, String transactionType,
                               String correspondent, String description, String amount, Set<Tag> tags) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.date = date;
        this.transactionType = transactionType;
        this.correspondent = correspondent;
        this.description = description;
        this.amount = amount;
        this.tags = tags;
    }

    @Override
    public String toString() {
        return id + accountNumber + correspondent + date + transactionType  + amount + description ;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public int getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
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
