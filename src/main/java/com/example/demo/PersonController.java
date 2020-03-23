package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
class PersonController {

    private final PersonRepository repository;

    PersonController(PersonRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/persons")
    List<Person> all() {

        List<Person> persons = repository.findAll();
        return persons;
    }


    @GetMapping("/person/{id}")
    Person one(@PathVariable Long id) {

        Person person = repository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
        return person;

    }


    @PostMapping("/person")
    Person addPerson(@Valid @RequestBody Person newPerson) {
        return repository.save(newPerson);
    }


    @PutMapping("/person/{id}")
    Person changePerson(@Valid @RequestBody Person incomingPerson, @PathVariable Long id) {

        Optional<Person> existingPerson = repository.findById(id);

        if (!existingPerson.isPresent()) {
            throw new PersonNotFoundException(id);
        }
        Person person = existingPerson.get();
        person.setFirstName(incomingPerson.getFirstName());
        person.setLastName(incomingPerson.getLastName());
        person.setSex(incomingPerson.getSex());
        person.setDateOfBirth(incomingPerson.getDateOfBirth());
        person.setBiologicalMotherId(incomingPerson.getBiologicalMotherId());
        person.setBiologicalFatherId(incomingPerson.getBiologicalFatherId());
        return repository.save(person);
    }
}
