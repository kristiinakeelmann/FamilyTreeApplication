package com.example.demo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StatisticsService {


    public List<Person> filterGender(List<Person> allPersons, String gender) {
        List<Person> women = new ArrayList<>();
        List<Person> men = new ArrayList<>();
        for (int i = 0; i < allPersons.size(); i++) {
            Person currentPerson = allPersons.get(i);
            if (currentPerson.getSex() != null) {
                if (currentPerson.getSex().equals("female") && gender.equals("female")) {
                    women.add(currentPerson);
                }
                if (currentPerson.getSex().equals("male") && gender.equals("male")) {
                    men.add(currentPerson);
                }
            }
        }
            if (gender == "female") {
                return women;
            } else {
                return men;
            }



    }


    public Person youngestAunt(List<Person> allPersons) {

        PersonFinder personFinder = new PersonFinder(allPersons);

        List<Person> allAunts = new ArrayList<>();

        for (int i = 0; i < allPersons.size(); i++) {
            Person currentPerson = allPersons.get(i);
            List<Person> cousins = personFinder.findCousins(currentPerson);
            allAunts.addAll(filterGender(cousins, "female"));
        }

        if (allAunts.size() != 0) {

            Person currentlyYoungest = allAunts.get(0);

            for (int i = 1; i < allAunts.size(); i++) {
                Person currentPerson = allAunts.get(i);
                LocalDate currentPersonDateOfBirth = currentPerson.getDateOfBirth();
                LocalDate currentlyYoungestDateOfBirth = currentlyYoungest.getDateOfBirth();
                if (currentPersonDateOfBirth != null && currentlyYoungestDateOfBirth != null) {
                    if (currentPersonDateOfBirth.compareTo(currentlyYoungestDateOfBirth) > 0) {
                        currentlyYoungest = currentPerson;
                    }
                }
            }
            return currentlyYoungest;

        } else return null;
    }


}
