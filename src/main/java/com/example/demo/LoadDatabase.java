package com.example.demo;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
@Slf4j
class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(PersonRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Person("Krstiina", "Keelmann", "female", LocalDate.parse("1992-08-06"), 2L, 3L)));
            log.info("Preloading " + repository.save(new Person("Rutt", "Keelmann", "female", LocalDate.parse("1981-12-01"), 6L, 7L)));
            log.info("Preloading " + repository.save(new Person("Toivo", "Keelmann", "male", LocalDate.parse("1992-08-06"), 8L, 9L)));
            log.info("Preloading " + repository.save(new Person("Krista", "Keelmann", "female", LocalDate.parse("1981-12-01"), 2L, 3L)));
            log.info("Preloading " + repository.save(new Person("Ragne", "Lill", "female", LocalDate.parse("1981-12-01"), 2L, 3L)));
            log.info("Preloading " + repository.save(new Person("Heljo", "Igarik", "female", LocalDate.parse("1981-12-01"), null, null)));
            log.info("Preloading " + repository.save(new Person("Elmar", "Igarik", "male", LocalDate.parse("1981-12-01"), null, null)));
            log.info("Preloading " + repository.save(new Person("Rea", "Keelmann", "female", LocalDate.parse("1981-12-01"), null, null)));
            log.info("Preloading " + repository.save(new Person("Väino", "Keelmann", "male", LocalDate.parse("1981-12-01"), null, null)));
            log.info("Preloading " + repository.save(new Person("Kaspar", "Keelmann", "male", LocalDate.parse("1981-12-01"), 2L, 3L)));
            log.info("Preloading " + repository.save(new Person("Reigo", "Keelmann", "male", LocalDate.parse("1981-12-01"), 2L, 3L)));

        };
    }
}
