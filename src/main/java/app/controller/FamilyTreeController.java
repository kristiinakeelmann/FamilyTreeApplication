package app.controller;

import app.entity.FamilyTree;
import app.entity.Person;
import app.repository.PersonRepository;
import app.service.FamilyTreeGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class FamilyTreeController {

    private FamilyTreeGenerator generator = new FamilyTreeGenerator();
    private final PersonRepository repository;

    FamilyTreeController(PersonRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/api/familytree/{id}")
    FamilyTree generateFamilyTree(@PathVariable Long id) {

        List<Person> allPersons = repository.findAll();

        return generator.create(allPersons, id);

    }

}


