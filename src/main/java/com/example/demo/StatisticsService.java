package com.example.demo;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
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

    private Person getYoungestPerson(List<Person> allPersons) {
        if (allPersons.size() != 0) {

            Person currentlyYoungest = allPersons.get(0);

            for (int i = 1; i < allPersons.size(); i++) {
                Person currentPerson = allPersons.get(i);
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

    public Person youngestAunt(List<Person> allPersons) {

        PersonFinder personFinder = new PersonFinder(allPersons);

        List<Person> allAunts = new ArrayList<>();

        for (int i = 0; i < allPersons.size(); i++) {
            Person currentPerson = allPersons.get(i);
            List<Person> cousins = personFinder.findCousins(currentPerson);
            allAunts.addAll(filterGender(cousins, "female"));
        }

        return getYoungestPerson(allAunts);
    }

    public Person youngestUncle(List<Person> allPersons) {

        PersonFinder personFinder = new PersonFinder(allPersons);

        List<Person> allUncles = new ArrayList<>();

        for (int i = 0; i < allPersons.size(); i++) {
            Person currentPerson = allPersons.get(i);
            List<Person> cousins = personFinder.findCousins(currentPerson);
            allUncles.addAll(filterGender(cousins, "male"));
        }

        return getYoungestPerson(allUncles);
    }


    public Integer birthOrder(List<Person> allPersons, Long selectedPersonId) {

        PersonFinder personFinder = new PersonFinder(allPersons);
        Person selectedPerson = personFinder.findPerson(selectedPersonId);
        List<Person> siblings = personFinder.findSiblings(selectedPerson);
        List<Person> siblingsAndSelectedPerson = new ArrayList<>();
        siblingsAndSelectedPerson.addAll(siblings);
        siblingsAndSelectedPerson.add(selectedPerson);

        if (siblings.size() != 0) {
            siblingsAndSelectedPerson.sort(Comparator.comparing(Person::getDateOfBirth));
        }

        Integer selectedPersonIndex = siblingsAndSelectedPerson.indexOf(selectedPerson) + 1;
        return selectedPersonIndex;
    }


    public List<Person> findAncestors(List<Person> allPersons, Long selectedPersonId) {

        PersonFinder personFinder = new PersonFinder(allPersons);
        List<Person> parents = new ArrayList<>();
        List<Person> ancestors = new ArrayList<>();
        Person selectedPerson = personFinder.findPerson(selectedPersonId);

        parents.addAll(personFinder.findParents(selectedPerson));
        for (int i = 0; i < parents.size(); i++) {
            Person currentParent = parents.get(i);
            List<Person> parentAncestors = findAncestors(allPersons, currentParent.getId());
            ancestors.addAll(parentAncestors);
        }
        ancestors.addAll(parents);
        return ancestors;
    }

    public List<String> findAncestorsNames(List<Person> allPersons, Long selectedPersonId) {

        List<Person> ancestors = findAncestors(allPersons, selectedPersonId);
        List<String> ancestorsNames = new ArrayList<String>();

        for (int i = 0; i < ancestors.size(); i++) {
            Person currentPerson = ancestors.get(i);
            ancestorsNames.add(currentPerson.getFirstName() + ' ' + currentPerson.getLastName() + ' ');
        }

        return ancestorsNames;
    }

    public Person mostAncestors(List<Person> allPersons) {
        PersonFinder personFinder = new PersonFinder(allPersons);
        Person currentlyMostAncestors = allPersons.get(0);

        if (allPersons.size() != 0 && personFinder.findParents(currentlyMostAncestors).size() != 0) {
            for (int i = 1; i < allPersons.size(); i++) {
                Person currentPerson = allPersons.get(i);
                List<Person> currentPersonAncestors = findAncestors(allPersons, currentPerson.getId());
                List<Person> currentlyMostAncestorsAncestors = findAncestors(allPersons, currentlyMostAncestors.getId());
                if (currentPersonAncestors.size() > currentlyMostAncestorsAncestors.size()) {
                    currentlyMostAncestors = currentPerson;
                }
            }
            return currentlyMostAncestors;

        } else return null;

    }
}
