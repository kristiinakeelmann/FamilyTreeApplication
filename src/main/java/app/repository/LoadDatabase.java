package app.repository;

import app.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
@Slf4j
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(PersonRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Person("Kristiina", "Keelmann", "female", LocalDate.parse("1992-08-06"), null, 2L, 3L)));
            log.info("Preloading " + repository.save(new Person("Rutt", "Keelmann", "female", LocalDate.parse("1950-12-01"), null, 6L, 7L)));
            log.info("Preloading " + repository.save(new Person("Toivo", "Keelmann", "male", LocalDate.parse("1950-08-06"), null, 8L, 9L)));
            log.info("Preloading " + repository.save(new Person("Krista", "Keelmann", "female", LocalDate.parse("1983-12-01"), null, 2L, 3L)));
            log.info("Preloading " + repository.save(new Person("Ragne", "Lill", "female", LocalDate.parse("1981-12-01"), null, 2L, 3L)));
            log.info("Preloading " + repository.save(new Person("Heljo", "Igarik", "female", LocalDate.parse("1920-12-01"), null, null, null)));
            log.info("Preloading " + repository.save(new Person("Elmar", "Igarik", "male", LocalDate.parse("1920-12-01"), LocalDate.parse("1980-01-01"), null, null)));
            log.info("Preloading " + repository.save(new Person("Rea", "Keelmann", "female", LocalDate.parse("1930-12-01"), LocalDate.parse("1980-01-01"), null, null)));
            log.info("Preloading " + repository.save(new Person("Väino", "Keelmann", "male", LocalDate.parse("1925-12-01"), LocalDate.parse("2019-12-20"), null, null)));
            log.info("Preloading " + repository.save(new Person("Kaspar", "Keelmann", "male", LocalDate.parse("1981-12-01"), null, 2L, 3L)));
            log.info("Preloading " + repository.save(new Person("Reigo", "Keelmann", "male", LocalDate.parse("1994-12-01"), LocalDate.parse("2017-04-10"), 2L, 3L)));
            log.info("Preloading " + repository.save(new Person("Katarina", "Lill", "female", LocalDate.parse("2008-12-01"), null, 5L, null)));
            log.info("Preloading " + repository.save(new Person("Elisabeth", "Lill", "female", LocalDate.parse("2004-12-01"), null, 5L, null)));
            log.info("Preloading " + repository.save(new Person("Martin", "Keelmann", "male", LocalDate.parse("2007-12-01"), null, null, 10L)));
            log.info("Preloading " + repository.save(new Person("Karolin", "Keelmann", "female", LocalDate.parse("2005-12-01"), null, null, 10L)));
        };
    }
}
