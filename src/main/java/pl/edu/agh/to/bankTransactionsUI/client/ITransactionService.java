package pl.edu.agh.to.bankTransactionsUI.client;

import pl.edu.agh.to.bankTransactionsUI.view.ReceivedTransaction;
import pl.edu.agh.to.bankTransactionsUI.view.Tag;
import retrofit2.Call;
import retrofit2.http.*;
import java.util.List;
import java.util.Set;

public interface ITransactionService {

    @GET("/transactions")
    Call<List<ReceivedTransaction>> getTransactions(@Query("tagNames") List<String> tagNames);

    @PUT("/transactions/{id}/tags")
    Call<Void> updateTransactionTags(@Path("id") int id, @Body Set<Tag> tags);
}
