package pl.edu.agh.to.bankTransactions.transaction;


import lombok.Setter;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Entity
public class Tag {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    @ManyToMany(mappedBy = "tags")
    private Set<Transaction> transactions = new HashSet<>();

    public Tag() {}

    public Tag(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}



