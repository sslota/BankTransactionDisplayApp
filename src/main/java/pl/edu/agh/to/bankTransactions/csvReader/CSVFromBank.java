package pl.edu.agh.to.bankTransactions.csvReader;

import pl.edu.agh.to.bankTransactions.transaction.Transaction;

public interface CSVFromBank {

    Transaction createTransactionRecord(String[] data);
}
