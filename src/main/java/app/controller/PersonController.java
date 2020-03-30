package app.controller;

import app.entity.Person;
import app.entity.PersonNotFoundException;
import app.repository.PersonRepository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
public class PersonController {

    private final PersonRepository repository;

    PersonController(PersonRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/api/persons")
    List<Person> all() {

        List<Person> persons = repository.findAll();
        return persons;
    }


    @GetMapping("/api/person/{id}")
    Person one(@PathVariable Long id) {

        Person person = repository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
        return person;

    }


    @PostMapping("/api/person")
    Person addPerson(@Valid @RequestBody Person newPerson) {
        return repository.save(newPerson);
    }


    @PutMapping("/api/person/{id}")
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
        person.setDateOfDeath(incomingPerson.getDateOfDeath());
        person.setBiologicalMotherId(incomingPerson.getBiologicalMotherId());
        person.setBiologicalFatherId(incomingPerson.getBiologicalFatherId());
        return repository.save(person);
    }
}
