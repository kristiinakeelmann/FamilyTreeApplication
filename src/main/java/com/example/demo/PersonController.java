package com.example.demo;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;


@RestController
class PersonController {

    private final PersonRepository repository;

    private final PersonModelAssembler assembler;

    PersonController(PersonRepository repository, PersonModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }


    @GetMapping("/persons")
    CollectionModel<EntityModel<Person>> all() {

        List<EntityModel<Person>> persons = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return new CollectionModel<>(persons,
                linkTo(methodOn(PersonController.class).all()).withSelfRel());
    }


    @GetMapping("/persons/{id}")
    EntityModel<Person> one(@PathVariable Long id) {

        Person person = repository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));

        return assembler.toModel(person);

    }


    @PostMapping("/persons")
    Person addPerson(@RequestBody Person newPerson) {
        return repository.save(newPerson);
    }


    @PutMapping("/persons/{id}")
    Person changePerson(@RequestBody Person newPerson, @PathVariable Long id) {

        return repository.findById(id)
                .map(person -> {
                    person.setFirstName(newPerson.getFirstName());
                    person.setLastName(newPerson.getLastName());
                    person.setGender(newPerson.getGender());
                    person.setDateOfBirth(newPerson.getDateOfBirth());
                    person.setBiologicalMotherId(newPerson.getBiologicalMotherId());
                    person.setBiologicalFatherId(newPerson.getBiologicalFatherId());
                    return repository.save(person);
                })
                .orElseGet(() -> {
                    newPerson.setId(id);
                    return repository.save(newPerson);
                });
    }

}