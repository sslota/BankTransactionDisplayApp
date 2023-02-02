package pl.edu.agh.to.bankTransactionsUI.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to.bankTransactions.transaction.TransactionRepository;
import pl.edu.agh.to.bankTransactionsUI.client.TransactionApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class SingleRecordController {

    @Autowired
    private TransactionApi transactionApi;
    @Autowired
    private TransactionRepository transactionRepository;

    private int id;

    @FXML
    private CheckBox checkbox1;

    @FXML
    private CheckBox checkbox2;

    @FXML
    private CheckBox checkbox3;

    @FXML
    private Label idField;

    @FXML
    private Label accountNumberField;

    @FXML
    private Label correspondentField;

    @FXML
    private Label dateField;

    @FXML
    private Label transactionTypeField;

    @FXML
    private Label amountField;

    @FXML
    private Label descriptionField;

    @FXML
    private Label tagsField;

    public void setValues(Transaction transaction) {
        idField.setText(String.valueOf(transaction.getId()));
        accountNumberField.setText(transaction.getAccountNumber());
        correspondentField.setText(transaction.getCorrespondent());
        dateField.setText(transaction.getDate());
        transactionTypeField.setText(transaction.getTransactionType());
        amountField.setText(transaction.getAmount());
        descriptionField.setText(transaction.getDescription());
        tagsField.setText(transaction.getTags().stream().map(Tag::getName).collect(Collectors.joining(", ")));
        this.id = transaction.getId();
    }

    public List<String> getSelectedTags() {
        List<String> selectedTags = new ArrayList<>();
        if (checkbox1.isSelected()) {
            selectedTags.add(checkbox1.getText());
        }
        if (checkbox2.isSelected()) {
            selectedTags.add(checkbox2.getText());
        }
        if (checkbox3.isSelected()) {
            selectedTags.add(checkbox3.getText());
        }
        return selectedTags;
    }

    @FXML
    public void saveAndClose(ActionEvent event) {
        List<String> selectedTags = getSelectedTags();
        Set<Tag> tags = selectedTags.stream()
                .map(Tag::new)
                .collect(Collectors.toSet());
        transactionApi.updateTransactionTags(id, tags);
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    public void setManager(TransactionApi transactionApi) {
        this.transactionApi = transactionApi;
    }
}
