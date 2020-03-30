package app.controller;

import app.entity.Person;
import app.repository.PersonRepository;
import app.service.StatisticsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class StatisticsController {

    private StatisticsService statisticsService = new StatisticsService();
    private final PersonRepository repository;

    StatisticsController(PersonRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/api/youngestaunt")
    Person findYoungestAunt() {

        List<Person> allPersons = repository.findAll();
        return statisticsService.youngestAunt(allPersons);
    }

    @GetMapping("/api/youngestuncle")
    Person findYoungestUncle() {

        List<Person> allPersons = repository.findAll();
        return statisticsService.youngestUncle(allPersons);
    }

    @GetMapping("/api/birthorder/{id}")
    Integer getBirthOrder(@PathVariable Long id) {

        List<Person> allPersons = repository.findAll();
        return statisticsService.birthOrder(allPersons, id);
    }

    @GetMapping("/api/mostancestors")
    Person mostAncestors() {

        List<Person> allPersons = repository.findAll();
        return statisticsService.mostAncestors(allPersons);
    }

    @GetMapping("/api/ancestornames/{id}")
    List <String> ancestorNames(@PathVariable Long id) {

        List<Person> allPersons = repository.findAll();
        return statisticsService.findAncestorsNames(allPersons, id);
    }
}
