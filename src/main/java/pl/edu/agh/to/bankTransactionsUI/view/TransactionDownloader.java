package pl.edu.agh.to.bankTransactionsUI.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.edu.agh.to.bankTransactionsUI.client.Client;
import pl.edu.agh.to.bankTransactionsUI.client.TransactionApi;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionDownloader {

    private Client client;

    private TransactionApi transactionApi;

    public TransactionDownloader(Client client, TransactionApi transactionApi) {
        this.client = client;
        this.transactionApi = transactionApi;
    }

    public ObservableList<Transaction> getTransactions() {
        List<ReceivedTransaction> receivedTransactions = transactionApi.getAllTransactions(new ArrayList<>());
        return receivedTransactions.stream()
                .map(t -> new Transaction(t.getId(), t.getAccountNumber(), t.getDate(),
                        t.getTransactionType(), t.getCorrespondent(), t.getDescription(),
                        t.getAmount(), t.getTags()))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
}
