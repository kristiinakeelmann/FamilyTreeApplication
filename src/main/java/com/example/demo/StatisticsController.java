package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class StatisticsController {

    private StatisticsService statisticsService = new StatisticsService();
    private final PersonRepository repository;

    StatisticsController(PersonRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/youngestaunt")
    Person findYoungestAunt() {

        List<Person> allPersons = repository.findAll();
        return statisticsService.youngestAunt(allPersons);
    }

    @GetMapping("/youngestuncle")
    Person findYoungestUncle() {

        List<Person> allPersons = repository.findAll();
        return statisticsService.youngestUncle(allPersons);
    }

}


