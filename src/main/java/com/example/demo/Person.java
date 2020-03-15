package com.example.demo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Entity
class Person {

    private @Id
    @GeneratedValue
    Long id;
    private String firstName;
    private String lastName;
    private String gender;
    private LocalDate dateOfBirth;
    private Long biologicalMotherId;
    private Long biologicalFatherId;

    Person() {
    }

    Person(String firstName, String lastName, String gender, LocalDate dateOfBirth, Long biologicalMotherId, Long biologicalFatherId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.biologicalMotherId = biologicalMotherId;
        this.biologicalFatherId = biologicalFatherId;
    }
}