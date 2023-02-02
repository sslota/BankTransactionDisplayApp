package pl.edu.agh.to.bankTransactionsUI.view;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.util.Set;


public class Transaction {

    private IntegerProperty id;
    private StringProperty accountNumber;
    private StringProperty correspondent;
    private StringProperty date;
    private StringProperty transactionType;
    private StringProperty amount;
    private StringProperty description;
    private Set<Tag> tags;

    public Transaction(int id, String accountNumber, String date, String transactionType,
                       String correspondent, String description, String amount, Set<Tag> tags) {
        this.id = new SimpleIntegerProperty(id);
        this.accountNumber = new SimpleStringProperty(accountNumber);
        this.date = new SimpleStringProperty(date);
        this.transactionType = new SimpleStringProperty(transactionType);
        this.correspondent = new SimpleStringProperty(correspondent);
        this.description = new SimpleStringProperty(description);
        this.amount = new SimpleStringProperty(amount);
        this.tags = tags;
        }

    public IntegerProperty getIdProperty() {
        return id;
    }

    public StringProperty getAccountNumberProperty() {
        return accountNumber;
    }

    public StringProperty getCorrespondentProperty() {
        return correspondent;
    }

    public StringProperty getDateProperty() {
        return date;
    }

    public StringProperty getTransactionTypeProperty() {
        return transactionType;
    }

    public StringProperty getAmountProperty() {
        return amount;
    }

    public StringProperty getDescriptionProperty() {
        return description;
    }

    @Override
    public String toString() {
        return id.get() + accountNumber.get() + correspondent.get() + date.get() + transactionType.get()  + amount.get() + description.get() ;
    }

    public int getId() {
        return id.get();
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public String getAccountNumber() {
        return accountNumber.get();
    }

    public String getCorrespondent() {
        return correspondent.get();
    }

    public String getDate() {
        return date.get();
    }

    public String getTransactionType() {
        return transactionType.get();
    }

    public String getAmount() {
        return amount.get();
    }

    public String getDescription() {
        return description.get();
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }
}
