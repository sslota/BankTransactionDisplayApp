package pl.edu.agh.to.bankTransactionsUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pl.edu.agh.to.bankTransactionsUI.client.Client;
import pl.edu.agh.to.bankTransactionsUI.client.TransactionApi;
import pl.edu.agh.to.bankTransactionsUI.view.TransactionTable;
import pl.edu.agh.to.bankTransactionsUI.view.TransactionTableController;
import pl.edu.agh.to.bankTransactionsUI.view.TransactionDownloader;
import java.io.IOException;

public class TransactionTableApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        var modelTable = new TransactionTable();
        Client client = new Client();
        TransactionApi transactionApi = new TransactionApi(client);
        configureModel(modelTable,client, transactionApi);
        try {
            var loader = new FXMLLoader();
            loader.setLocation(TransactionTableApp.class.getResource("/view/tableView.fxml"));
            BorderPane rootLayout = loader.load();

            TransactionTableController controller = loader.getController();
            controller.setModel(modelTable,client, transactionApi);

            configureStage(primaryStage, rootLayout);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void configureStage(Stage primaryStage, BorderPane rootLayout) {
        var scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.setTitle("KoluniosBankonieros");
        primaryStage.minWidthProperty().bind(rootLayout.minWidthProperty());
        primaryStage.minHeightProperty().bind(rootLayout.minHeightProperty());
    }

    private void configureModel(TransactionTable transactionTable, Client client, TransactionApi transactionApi) {
        fillModelTable(transactionTable, client, transactionApi);
    }

    private void fillModelTable(TransactionTable transactionTable, Client client, TransactionApi transactionApi) {
        var transactionDownloader = new TransactionDownloader(client, transactionApi);
        transactionTable.addTransactions(transactionDownloader.getTransactions());
    }
}
