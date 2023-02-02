package pl.edu.agh.to.bankTransactions.csvReader;

import pl.edu.agh.to.bankTransactions.transaction.Transaction;

public class ReaderContext {
    private CSVFromBank csvReader;
    public void setReader(CSVFromBank csvReader){
        this.csvReader=csvReader;
    }
    public Transaction readCSVFile(String[] data){
        return this.csvReader.createTransactionRecord(data);
    }
}
