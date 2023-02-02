package pl.edu.agh.to.bankTransactions.transaction;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping(path = "/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final TagService tagService;

    public TransactionController(TransactionService transactionService, TagService tagService) {
        this.transactionService = transactionService;
        this.tagService = tagService;
    }

    @GetMapping
    public List<Transaction> getTransactions(@RequestParam(required = false) List<String> tags) {
        if (tags == null || tags.isEmpty()) {
            return transactionService.getTransactions();
        } else {
            return transactionService.filterTransactionsByTags(tags);
        }
    }

    @PostMapping
    public void registerNewTransaction(@RequestBody List<Transaction> transactions){
        transactionService.addNewTransactions(transactions);
    }

    @PutMapping("/{id}/tags")
    public void updateTransactionTags(@PathVariable int id, @RequestBody List<TagDTO> tags) {
        System.out.println("UPDATING TAGS FOR -> " +  id);
        Transaction transaction = transactionService.getTransaction(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find transaction with id: " + id));

        HashSet<Tag> tagsHashSet = new HashSet<>();
        for(TagDTO tagDTO : tags) {
            Tag tag = tagService.getTag(tagDTO.getName())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find tag: " + tagDTO.getName()));
            tagsHashSet.add(tag);
        }
        transaction.setTags(tagsHashSet);
        transactionService.updateTransaction(transaction);
    }

    @GetMapping("/filterbytags")
    public List<Transaction> filterTransactionsByTags(@RequestParam List<String> tagNames) {
        return transactionService.filterTransactionsByTags(tagNames);
    }
}