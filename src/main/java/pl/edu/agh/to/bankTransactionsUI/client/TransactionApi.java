package pl.edu.agh.to.bankTransactionsUI.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.to.bankTransactionsUI.view.ReceivedTransaction;
import pl.edu.agh.to.bankTransactionsUI.view.Tag;
import retrofit2.Call;
import retrofit2.Response;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@RestController
public class TransactionApi {

    private Client retrofitClient;

    @Autowired
    public TransactionApi(Client retrofitClient) {
        this.retrofitClient = retrofitClient;
    }

    private ITransactionService getTransactionServiceImpl() {
        return retrofitClient.getRetrofitClient().create(ITransactionService.class);
    }

    public List<ReceivedTransaction> getAllTransactions(List<String> tagNames) {
        ITransactionService service = getTransactionServiceImpl();
        Response<List<ReceivedTransaction>> response = null;
        try {
            response = service.getTransactions(tagNames).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.body();
    }

    public void updateTransactionTags(int id, Set<Tag> tags) {
        ITransactionService service = getTransactionServiceImpl();
        Call<Void> call = service.updateTransactionTags(id, tags);
        try {
            call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}