package pl.edu.agh.to.bankTransactions.monitorDirectory;

import org.springframework.boot.devtools.filewatch.FileSystemWatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.edu.agh.to.bankTransactions.transaction.TransactionService;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

@Configuration
public class FileWatcherConfig {
    @Bean
    public FileSystemWatcher fileSystemWatcher(TransactionService transactionService) {

        FileSystemWatcher fileSystemWatcher = new FileSystemWatcher(true, Duration.ofMillis(50L), Duration.ofMillis(30L));
        Path currentPath = Paths.get(System.getProperty("user.dir"));
        Path storagePath = Paths.get(currentPath.toString(), "src", "main", "resources", "storage");
        fileSystemWatcher.addSourceDirectory(new File(String.valueOf(storagePath)));
        fileSystemWatcher.addListener(new MyFileChangeListener(transactionService));
        fileSystemWatcher.start();
        System.out.println("fileSystemWatcher started working");
        return fileSystemWatcher;
    }
}