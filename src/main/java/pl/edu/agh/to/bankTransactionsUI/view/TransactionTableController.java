package pl.edu.agh.to.bankTransactionsUI.view;


import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pl.edu.agh.to.bankTransactionsUI.client.Client;
import pl.edu.agh.to.bankTransactionsUI.client.TransactionApi;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


public class TransactionTableController {

    private Client client;
    private TransactionApi transactionApi;
    private Set<String> selectedTags = new HashSet<>();

    @FXML
    private CheckBox checkbox1;
    @FXML
    private CheckBox checkbox2;
    @FXML
    private CheckBox checkbox3;
    @FXML
    private TableView<Transaction> modelTableView = new TableView<>();
    @FXML
    private TableColumn<Transaction, String> idCol;
    @FXML
    private TableColumn<Transaction, String> accountNumberCol;
    @FXML
    private TableColumn<Transaction, String> correspondentCol;
    @FXML
    private TableColumn<Transaction, String> dateCol;
    @FXML
    private TableColumn<Transaction, String> transactionTypeCol;
    @FXML
    private TableColumn<Transaction, String> amountCol;
    @FXML
    private TableColumn<Transaction, String> descriptionCol;
    @FXML
    private TableColumn<Transaction, String> tagsCol;

    @FXML
    public void initialize() {

        tagsCol.setCellValueFactory(param -> {
            Set<Tag> tags = param.getValue().getTags();
            String tagNames = tags.stream().map(Tag::getName).collect(Collectors.joining(", "));
            return new SimpleStringProperty(tagNames);
        });

        idCol.setCellValueFactory(
                new PropertyValueFactory<>("id"));

        accountNumberCol.setCellValueFactory(
                new PropertyValueFactory<>("accountNumber"));

        correspondentCol.setCellValueFactory(
                new PropertyValueFactory<>("correspondent"));

        dateCol.setCellValueFactory(
                new PropertyValueFactory<>("date"));

        transactionTypeCol.setCellValueFactory(
                new PropertyValueFactory<>("transactionType"));

        amountCol.setCellValueFactory(
                new PropertyValueFactory<>("amount"));

        descriptionCol.setCellValueFactory(
                new PropertyValueFactory<>("description"));
    }

    public void setModel(TransactionTable transactionTable, Client client, TransactionApi transactionApi) {
        modelTableView.setItems(transactionTable.getTransactions());
        this.client=client;
        this.transactionApi = transactionApi;
    }

    @FXML
    private void refresh(ActionEvent event) {
        event.consume();
        TransactionTable transactionTable = new TransactionTable();
        var transactionDownloader = new TransactionDownloader(client, transactionApi);
        transactionTable.addTransactions(transactionDownloader.getTransactions());
        modelTableView.setItems(transactionTable.getTransactions());
        modelTableView.refresh();
    }

    @FXML
    public void handleMouseClick(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
            Transaction selectedTransaction = modelTableView.getSelectionModel().getSelectedItem();
            if (selectedTransaction != null) {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(TransactionTableController.class.getResource("/view/singlerecord.fxml"));
                    AnchorPane rootLayout = loader.load();
                    SingleRecordController singleRecordController = loader.getController();
                    singleRecordController.setManager(transactionApi);
                    singleRecordController.setValues(selectedTransaction);
                    Scene scene = new Scene(rootLayout);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Single Record");
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("No selected transaction");
            }
        }
    }
}
