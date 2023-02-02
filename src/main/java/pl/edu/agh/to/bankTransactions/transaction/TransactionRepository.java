package pl.edu.agh.to.bankTransactions.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    Optional<Transaction> findById(int id);

    @Query("SELECT t FROM Transaction t JOIN t.tags tag WHERE tag.name IN :tagNames")
    List<Transaction> findByTagNames(List<String> tagNames);
}