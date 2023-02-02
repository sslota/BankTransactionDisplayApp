package pl.edu.agh.to.bankTransactions.csvReader;

import pl.edu.agh.to.bankTransactions.transaction.Transaction;

public class CSVFromPKOBP implements CSVFromBank{
    public Transaction createTransactionRecord(String[] data) {
        String accountNumber = "accountNumber";
        String date = data[0];
        String transactionType = data[2];
        String correspondent = "correspondent";
        String description = data[6];
        String amount = data[3];

        return new Transaction(accountNumber, date, transactionType,
                correspondent, description, amount);
    }
}
