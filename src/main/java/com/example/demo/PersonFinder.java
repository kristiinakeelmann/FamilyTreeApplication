package com.example.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PersonFinder {

    private List<Person> allPersons;

    public Person findPerson(Long id) {
        Person person = null;
        for (int i = 0; i < allPersons.size(); i++) {
            Person currentPerson = allPersons.get(i);
            if (currentPerson.getId() == id) {
                person = currentPerson;
            }
        }
        return person;
    }

    public PersonFinder(List<Person> allPersons) {
        this.allPersons = allPersons;
    }

    public Person findMother(Person selectedPerson) {
        if (selectedPerson == null) {
            return null;
        }
        return findPerson(selectedPerson.getBiologicalMotherId());
    }

    public Person findFather(Person selectedPerson) {
        if (selectedPerson == null) {
            return null;
        }
        return findPerson(selectedPerson.getBiologicalFatherId());
    }

    public List<Person> findChildren(Person selectedPerson) {
        if (selectedPerson == null) {
            return Collections.emptyList();
        }
        List<Person> children = new ArrayList<>();
        for (int i = 0; i < allPersons.size(); i++) {
            Person currentPerson = allPersons.get(i);
            if (findPerson(currentPerson.getBiologicalFatherId()) == selectedPerson || findPerson(currentPerson.getBiologicalMotherId()) == selectedPerson) {
                children.add(currentPerson);
            }
        }
        return children;
    }

    public List<Person> findParents(Person selectedPerson) {
        if (selectedPerson == null) {
            return Collections.emptyList();
        }
        List<Person> parents = new ArrayList<>();
        parents.add(findMother(selectedPerson));
        parents.add(findFather(selectedPerson));
        return parents;
    }

    public List<Person> findSiblings(Person selectedPerson) {
        if (selectedPerson == null) {
            return Collections.emptyList();
        }
        List<Person> parents = findParents(selectedPerson);
        List<Person> siblings = new ArrayList<>();
        for (int i = 0; i < parents.size(); i++) {
            Person parent = parents.get(i);
            List<Person> parentChildren = findChildren(parent);
            for (int j = 0; j < parentChildren.size(); j++) {
                Person parentChild = parentChildren.get(j);
                if(parentChild != selectedPerson) {
                    siblings.add(parentChild);
                }
            }

        }
        return siblings;
    }

    public List<Person> findCousins(Person selectedPerson) {
        if (selectedPerson == null) {
            return Collections.emptyList();
        }
        List<Person> parents = findParents(selectedPerson);
        List<Person> cousins = new ArrayList<>();
        for (int i = 0; i < parents.size(); i++) {
            Person parent = parents.get(i);
            cousins.addAll(findSiblings(parent));
        }
        return cousins;

    }
}
