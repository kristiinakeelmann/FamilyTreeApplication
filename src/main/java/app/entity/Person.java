package app.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Entity
public class Person {

    private @Id
    @GeneratedValue
    Long id;
    @Size(min=2, message="First name should have atleast 2 characters")
    private String firstName;
    @Size(min=2, message="Last name should have atleast 2 characters")
    private String lastName;
    private String sex;
    @PastOrPresent(message="Birth date must be in the past or present")
    private LocalDate dateOfBirth;
    @PastOrPresent(message="Date of death must be in the past or present")
    private LocalDate dateOfDeath;
    private Long biologicalMotherId;
    private Long biologicalFatherId;

    public Person() {
    }


    public Person(String firstName, String lastName, String sex, LocalDate dateOfBirth, LocalDate dateOfDeath, Long biologicalMotherId, Long biologicalFatherId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.dateOfDeath = dateOfDeath;
        this.biologicalMotherId = biologicalMotherId;
        this.biologicalFatherId = biologicalFatherId;
    }


}