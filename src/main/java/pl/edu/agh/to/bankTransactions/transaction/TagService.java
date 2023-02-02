package pl.edu.agh.to.bankTransactions.transaction;

import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class TagService {
    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository){
        this.tagRepository = tagRepository;
    }

    public Optional<Tag> getTag(String name){
        return this.tagRepository.findByName(name);
    }
}

