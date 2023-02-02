package pl.edu.agh.to.bankTransactions.monitorDirectory;

import org.springframework.boot.devtools.filewatch.ChangedFile;
import org.springframework.boot.devtools.filewatch.ChangedFiles;
import org.springframework.boot.devtools.filewatch.FileChangeListener;
import org.springframework.stereotype.Component;
import pl.edu.agh.to.bankTransactions.csvReader.CSVReader;
import pl.edu.agh.to.bankTransactions.transaction.Transaction;
import pl.edu.agh.to.bankTransactions.transaction.TransactionService;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Set;

@Component
public class MyFileChangeListener implements FileChangeListener {

    TransactionService transactionService;
    public MyFileChangeListener(TransactionService transactionService) {
        this.transactionService=transactionService;
    }

    @Override
    public void onChange(Set<ChangedFiles> changeSet) {
        for(ChangedFiles changedFiles : changeSet) {
            for(ChangedFile changedFile: changedFiles.getFiles()) {
                System.out.println(changedFile);
                if(  (changedFile.getType().equals(ChangedFile.Type.MODIFY)
                        || changedFile.getType().equals(ChangedFile.Type.ADD)
                        || changedFile.getType().equals(ChangedFile.Type.DELETE) ) && isLocked(changedFile.getFile().toPath())) {
                    System.out.println("Operation: " + changedFile.getType()
                            + " On file: "+ changedFile.getFile().getName() + " is done");
                if (changedFile.getType().equals(ChangedFile.Type.ADD) && isLocked(changedFile.getFile().toPath())) {
                    CSVReader csvReader = new CSVReader();
                    List<Transaction> transactions;
                    transactions = csvReader.recordsFromCSVFile(changedFile.getFile().getName());
                    transactionService.addNewTransactions(transactions);
                }
                }
            }
        }
    }

    private boolean isLocked(Path path) {
        try (FileChannel ch = FileChannel.open(path, StandardOpenOption.WRITE);
             FileLock lock = ch.tryLock()) {
            return lock != null;
        } catch (IOException e) {
            return false;
        }
    }
}
