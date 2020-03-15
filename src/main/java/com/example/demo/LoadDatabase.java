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
            log.info("Preloading " + repository.save(new Person("Mart", "Kuusk", "male", LocalDate.parse("1992-08-06"), 2L, 2L)));
            log.info("Preloading " + repository.save(new Person("Mari", "Kuusk", "female", LocalDate.parse("1981-12-01"), 1L, 1L)));
        };
    }
}
