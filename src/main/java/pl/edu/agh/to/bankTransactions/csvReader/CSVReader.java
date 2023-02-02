package pl.edu.agh.to.bankTransactions.csvReader;

import pl.edu.agh.to.bankTransactions.transaction.Transaction;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    public List<Transaction> recordsFromCSVFile(String filename) {
        List<Transaction> records = new ArrayList<>();
        Path currentPath = Paths.get(System.getProperty("user.dir"));
        Path filePath = Paths.get(currentPath.toString(), "src", "main", "resources", "storage", filename);
        ReaderContext context = new ReaderContext();

            try (BufferedReader bufferedReader = Files.newBufferedReader(filePath))
        {
            String line = bufferedReader.readLine();
            if (filename.startsWith("history_csv_")){
                if (line !=null){
                    line = bufferedReader.readLine();
                }
                context.setReader(new CSVFromPKOBP());
            }
            else if(filename.startsWith("Historia_transkacji_csv_citi")){
                context.setReader(new CSVFromCitibank());
            }
            else if(filename.startsWith("Historia_transakcji_")){
                if (line !=null){
                    line = bufferedReader.readLine();
                }
                context.setReader(new CSVFromMillennium());
            }
            else{
                System.out.println("File type is not supported");
                line=null;
            }
            while (line != null) {
                String[] data = line.split(",");
                Transaction record = context.readCSVFile(data);
                records.add(record);
                line = bufferedReader.readLine();
            }
            for (Transaction record : records) {
                System.out.println(record);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }
}
