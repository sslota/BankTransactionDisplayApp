package pl.edu.agh.to.bankTransactions;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.edu.agh.to.bankTransactions.transaction.Tag;
import pl.edu.agh.to.bankTransactions.transaction.TagRepository;
import java.util.List;


@Configuration
public class AppConfigurator {

    @Bean
    CommandLineRunner init(TagRepository tagRepository) {
        return args -> {
            List<Tag> tags = tagRepository.findAll();
            if (tags.isEmpty()) {
                tagRepository.save(new Tag("checkbox1"));
                tagRepository.save(new Tag("checkbox2"));
                tagRepository.save(new Tag("checkbox3"));
            }
        };
    }
}
