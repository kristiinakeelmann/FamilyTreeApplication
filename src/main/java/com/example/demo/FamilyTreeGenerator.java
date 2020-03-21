package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class FamilyTreeGenerator {


    Person findPerson(List<Person> allPersons, Long personId) {

        Person person = null;
        for (int i = 0; i < allPersons.size(); i++) {
            Person currentPerson = allPersons.get(i);
            if (currentPerson.getId() == personId) {
                person = currentPerson;
            }
        }
        return person;
    }


    List<String> findChildrenForMother(List<Person> allPersons, Long personId) {

        List<String> children = new ArrayList<>();
        for (int i = 0; i < allPersons.size(); i++) {
            Person currentPerson = allPersons.get(i);
            if (currentPerson.getBiologicalMotherId() == personId) {
                children.add(currentPerson.getFirstName() + ' ' + currentPerson.getLastName());
            }
        }
        return children;
    }


    List<String> findChildrenForFather(List<Person> allPersons, Long personId) {

        List<String> children = new ArrayList<>();
        for (int i = 0; i < allPersons.size(); i++) {
            Person currentPerson = allPersons.get(i);
            if (currentPerson.getBiologicalFatherId() == personId) {
                children.add(currentPerson.getFirstName() + ' ' + currentPerson.getLastName());
            }
        }
        return children;
    }


    List<String> findSisters(List<Person> allPersons, Long personId) {

        Person selectedPerson = findPerson(allPersons, personId);

        List<String> sisters = new ArrayList<>();
        for (int i = 0; i < allPersons.size(); i++) {
            Person currentPerson = allPersons.get(i);
            if (currentPerson.getBiologicalMotherId() == selectedPerson.getBiologicalMotherId() && currentPerson.getSex() == "female" && currentPerson.getId() != selectedPerson.getId()) {
                sisters.add(currentPerson.getFirstName() + ' ' + currentPerson.getLastName());
            }
        }
        return sisters;
    }


    List<String> findBrothers(List<Person> allPersons, Long personId) {

        Person selectedPerson = findPerson(allPersons, personId);

        List<String> brothers = new ArrayList<>();
        for (int i = 0; i < allPersons.size(); i++) {
            Person currentPerson = allPersons.get(i);
            if (currentPerson.getBiologicalMotherId() == selectedPerson.getBiologicalMotherId() && currentPerson.getSex() == "male" && currentPerson.getId() != selectedPerson.getId()) {
                brothers.add(currentPerson.getFirstName() + ' ' + currentPerson.getLastName());
            }
        }
        return brothers;
    }


    List<String> findFatherSideAunts(List<Person> allPersons, Long personId) {

        Person selectedPerson = findPerson(allPersons, personId);
        Long selectedPersonFather = selectedPerson.getBiologicalFatherId();

        List<String> aunts = new ArrayList<>();
        if (selectedPerson.getBiologicalFatherId() != null) {
            aunts = findSisters(allPersons, selectedPersonFather);
        }
        return aunts;
    }


    List<String> findMotherSideAunts(List<Person> allPersons, Long personId) {

        Person selectedPerson = findPerson(allPersons, personId);
        Long selectedPersonMother = selectedPerson.getBiologicalMotherId();

        List<String> aunts = new ArrayList<>();
        if (selectedPerson.getBiologicalMotherId() != null) {
            aunts = findSisters(allPersons, selectedPersonMother);
        }
        return aunts;
    }


    List<String> findFatherSideUncles(List<Person> allPersons, Long personId) {

        Person selectedPerson = findPerson(allPersons, personId);
        Long selectedPersonFather = selectedPerson.getBiologicalFatherId();

        List<String> uncles = new ArrayList<>();
        if (selectedPerson.getBiologicalFatherId() != null) {
            uncles = findBrothers(allPersons, selectedPersonFather);
        }
        return uncles;
    }


    List<String> findMotherSideUncles(List<Person> allPersons, Long personId) {

        Person selectedPerson = findPerson(allPersons, personId);
        Long selectedPersonMother = selectedPerson.getBiologicalMotherId();

        List<String> uncles = new ArrayList<>();
        if (selectedPerson.getBiologicalMotherId() != null) {
            uncles = findBrothers(allPersons, selectedPersonMother);
        }
        return uncles;
    }


    FamilyTree create(List<Person> allPersons, Long selectedPersonId) {

        FamilyTree familyTree = new FamilyTree();

        Long personMotherId = null;
        Long personFatherId = null;

        Person selectedPerson = findPerson(allPersons, selectedPersonId);
        personMotherId = selectedPerson.getBiologicalMotherId();
        personFatherId = selectedPerson.getBiologicalFatherId();
        Person mother = findPerson(allPersons, personMotherId);
        Person father = findPerson(allPersons, personFatherId);

        Person motherMother = null;
        Person motherFather = null;
        Person fatherMother = null;
        Person fatherFather = null;

        if (mother != null) {
            motherMother = findPerson(allPersons, mother.getBiologicalMotherId());
            motherFather = findPerson(allPersons, mother.getBiologicalFatherId());
        }

        if (father != null) {
            fatherMother = findPerson(allPersons, father.getBiologicalMotherId());
            fatherFather = findPerson(allPersons, father.getBiologicalFatherId());
        }

        List<String> mothersChildren = findChildrenForMother(allPersons, selectedPersonId);
        List<String> fathersChildren = findChildrenForFather(allPersons, selectedPersonId);

        List<String> children = new ArrayList<String>();
        children.addAll(mothersChildren);
        children.addAll(fathersChildren);

        List<String> sisters = findSisters(allPersons, selectedPersonId);
        List<String> brothers = findBrothers(allPersons, selectedPersonId);
        List<String> fatherSideAunts = findFatherSideAunts(allPersons, selectedPersonId);
        List<String> motherSideAunts = findMotherSideAunts(allPersons, selectedPersonId);
        List<String> fatherSideUncles = findFatherSideUncles(allPersons, selectedPersonId);
        List<String> motherSideUncles = findMotherSideUncles(allPersons, selectedPersonId);

        List<String> uncles = new ArrayList<String>();
        uncles.addAll(motherSideUncles);
        uncles.addAll(fatherSideUncles);

        List<String> aunts = new ArrayList<String>();
        aunts.addAll(motherSideAunts);
        aunts.addAll(fatherSideAunts);

        familyTree.setSelectedPersonName(selectedPerson.getFirstName() + ' ' + selectedPerson.getLastName());

        if (mother != null) {
            familyTree.setMotherName(mother.getFirstName() + ' ' + mother.getLastName());
        }
        if (father != null) {
            familyTree.setFatherName(father.getFirstName() + ' ' + father.getLastName());
        }

        if (motherMother != null) {
            familyTree.setMotherMotherName(motherMother.getFirstName() + ' ' + motherMother.getLastName());
        }

        if (motherFather != null) {
            familyTree.setMotherFatherName(motherFather.getFirstName() + ' ' + motherFather.getLastName());
        }

        if (fatherMother != null) {
            familyTree.setFatherMotherName(fatherMother.getFirstName() + ' ' + fatherMother.getLastName());
        }

        if (fatherFather != null) {
            familyTree.setFatherFatherName(fatherFather.getFirstName() + ' ' + fatherFather.getLastName());
        }

        if (children.size() > 0) {
            familyTree.setChildren(children);
        }

        if (sisters.size() > 0) {
            familyTree.setSisters(sisters);
        }

        if (brothers.size() > 0) {
            familyTree.setBrothers(brothers);
        }

        if (uncles.size() > 0) {
            familyTree.setUncles(uncles);
        }

        if (aunts.size() > 0) {
            familyTree.setAunts(aunts);
        }

        return familyTree;
    }


}

