package com.example.demo;

import java.util.ArrayList;
import java.util.List;


public class FamilyTreeGenerator {

    FamilyTree create(List<Person> allPersons, Long selectedPersonId) {


        PersonFinder personFinder = new PersonFinder(allPersons);
        FamilyTree familyTree = new FamilyTree();

        Person selectedPerson = personFinder.findPerson(selectedPersonId);
        Person mother = personFinder.findMother(selectedPerson);
        Person father = personFinder.findFather(selectedPerson);
        Person motherMother = personFinder.findMother(mother);
        Person motherFather = personFinder.findFather(mother);
        Person fatherMother = personFinder.findMother(father);
        Person fatherFather = personFinder.findFather(father);

        List<Person> children = personFinder.findChildren(selectedPerson);
        List<String> childrenNames = new ArrayList<String>();
        for (int i = 0; i < children.size(); i++) {
            Person currentPerson = children.get(i);
            childrenNames.add(currentPerson.getFirstName() + ' ' + currentPerson.getLastName());
        }

        List<Person> sisters = personFinder.findSiblings(selectedPerson);
        List<String> sistersNames = new ArrayList<String>();
        for (int i = 0; i < sisters.size(); i++) {
            Person currentPerson = sisters.get(i);
            if (currentPerson.getSex() == "female") {
                sistersNames.add(currentPerson.getFirstName() + ' ' + currentPerson.getLastName());
            }
        }

        List<Person> brothers = personFinder.findSiblings(selectedPerson);
        List<String> brothersNames = new ArrayList<String>();
        for (int i = 0; i < brothers.size(); i++) {
            Person currentPerson = brothers.get(i);
            if (currentPerson.getSex() == "male") {
                brothersNames.add(currentPerson.getFirstName() + ' ' + currentPerson.getLastName());
            }
        }

        List<Person> aunts = personFinder.findCousins(selectedPerson);
        List<String> auntsNames = new ArrayList<String>();
        for (int i = 0; i < aunts.size(); i++) {
            Person currentPerson = aunts.get(i);
            if (currentPerson.getSex() == "female") {
                auntsNames.add(currentPerson.getFirstName() + ' ' + currentPerson.getLastName());
            }
        }

        List<Person> uncles = personFinder.findCousins(selectedPerson);
        List<String> unclesNames = new ArrayList<String>();
        for (int i = 0; i < uncles.size(); i++) {
            Person currentPerson = uncles.get(i);
            if (currentPerson.getSex() == "male") {
                unclesNames.add(currentPerson.getFirstName() + ' ' + currentPerson.getLastName());
            }
        }


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
            familyTree.setChildren(childrenNames);
        }

        if (sisters.size() > 0) {
            familyTree.setSisters(sistersNames);
        }

        if (brothers.size() > 0) {
            familyTree.setBrothers(brothersNames);
        }

        if (uncles.size() > 0) {
            familyTree.setUncles(unclesNames);
        }

        if (aunts.size() > 0) {
            familyTree.setAunts(auntsNames);
        }

        return familyTree;
    }


}
