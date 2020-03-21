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
    private String sex;
    private LocalDate dateOfBirth;
    private Long biologicalMotherId;
    private Long biologicalFatherId;

    Person() {
    }

    Person(String firstName, String lastName, String sex, LocalDate dateOfBirth, Long biologicalMotherId, Long biologicalFatherId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.biologicalMotherId = biologicalMotherId;
        this.biologicalFatherId = biologicalFatherId;
    }


}