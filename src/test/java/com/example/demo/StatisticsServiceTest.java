package com.example.demo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

public class StatisticsServiceTest {

    StatisticsService statisticsService = new StatisticsService();

    private FamilyTreeGenerator generator = new FamilyTreeGenerator();


    @Test
    public void youngestAunt() {

        Person youngestAunt = statisticsService.youngestAunt(family());
        String youngestAuntName = youngestAunt.getFirstName() + ' ' + youngestAunt.getLastName();
        Assert.assertEquals("Kristiina Keelmann", youngestAuntName);

    }

    @Test
    public void youngestUncle() {

        Person youngestUncle = statisticsService.youngestUncle(family());
        String youngestUncleName = youngestUncle.getFirstName() + ' ' + youngestUncle.getLastName();
        Assert.assertEquals("Reigo Keelmann", youngestUncleName);

    }

    @Test
    public void birthOrder() {

        int birthOrder = statisticsService.birthOrder(family(), 1L);
        Assert.assertEquals(4, birthOrder);

    }

    @Test
    public void birthOrderOneChild() {

        Person selectedPerson = new Person();
        selectedPerson.setId(1L);
        selectedPerson.setFirstName("Rutt");
        selectedPerson.setLastName("Keelmann");
        selectedPerson.setBiologicalMotherId(2L);
        selectedPerson.setBiologicalFatherId(3L);
        selectedPerson.setSex("female");
        selectedPerson.setDateOfBirth(LocalDate.of(1950, 12, 1));
        Person mother = new Person();
        mother.setId(2L);
        mother.setFirstName("Heljo");
        mother.setLastName("Igarik");
        mother.setDateOfBirth(LocalDate.of(1930, 5, 1));
        Person father = new Person();
        father.setId(3L);
        father.setFirstName("Elmar");
        father.setLastName("Igarik");
        father.setDateOfBirth(LocalDate.of(1930, 6, 22));

        List<Person> result = asList(selectedPerson, mother, father);

        int birthOrder = statisticsService.birthOrder(result, 1L);
        Assert.assertEquals(1, birthOrder);

    }

    @Test
    public void mostAncestors() {

        Person mostAncestors = statisticsService.mostAncestors(family());
        String mostAncestorsName = mostAncestors.getFirstName() + ' ' + mostAncestors.getLastName();
        Assert.assertEquals("Elisabeth Lill", mostAncestorsName);
    }

    @Test
    public void noAncestors(){

        Person firstPerson = new Person();
        firstPerson.setId(1L);
        firstPerson.setFirstName("Mari");
        firstPerson.setLastName("Maasikas");
        Person secondPerson = new Person();
        secondPerson.setId(2L);
        secondPerson.setFirstName("Tuuli");
        secondPerson.setLastName("Tihane");

        List<Person> result = asList(firstPerson, secondPerson);

        Person mostAncestors = statisticsService.mostAncestors(result);
        Assert.assertEquals(null, mostAncestors);
    }

    private List<Person> family() {

        Person selectedPerson = new Person();
        selectedPerson.setId(1L);
        selectedPerson.setFirstName("Kristiina");
        selectedPerson.setLastName("Keelmann");
        selectedPerson.setBiologicalMotherId(2L);
        selectedPerson.setBiologicalFatherId(3L);
        selectedPerson.setSex("female");
        selectedPerson.setDateOfBirth(LocalDate.of(1992, 6, 8));
        Person mother = new Person();
        mother.setId(2L);
        mother.setFirstName("Rutt");
        mother.setLastName("Keelmann");
        mother.setBiologicalMotherId(16L);
        mother.setBiologicalFatherId(17L);
        mother.setDateOfBirth(LocalDate.of(1950, 12, 1));
        Person father = new Person();
        father.setId(3L);
        father.setFirstName("Toivo");
        father.setLastName("Keelmann");
        father.setBiologicalMotherId(18L);
        father.setBiologicalFatherId(19L);
        father.setDateOfBirth(LocalDate.of(1950, 6, 22));
        Person firstSister = new Person();
        firstSister.setId(10L);
        firstSister.setFirstName("Ragne");
        firstSister.setLastName("Lill");
        firstSister.setBiologicalMotherId(2L);
        firstSister.setBiologicalFatherId(3L);
        firstSister.setSex("female");
        firstSister.setDateOfBirth(LocalDate.of(1978, 12, 28));
        Person secondSister = new Person();
        secondSister.setId(11L);
        secondSister.setFirstName("Krista");
        secondSister.setLastName("Keelmann");
        secondSister.setBiologicalMotherId(2L);
        secondSister.setBiologicalFatherId(3L);
        secondSister.setSex("female");
        secondSister.setDateOfBirth(LocalDate.of(1980, 7, 2));
        Person firstBrother = new Person();
        firstBrother.setId(12L);
        firstBrother.setFirstName("Kaspar");
        firstBrother.setLastName("Keelmann");
        firstBrother.setBiologicalMotherId(2L);
        firstBrother.setBiologicalFatherId(3L);
        firstBrother.setSex("male");
        firstBrother.setDateOfBirth(LocalDate.of(1979, 3, 23));
        Person secondBrother = new Person();
        secondBrother.setId(13L);
        secondBrother.setFirstName("Reigo");
        secondBrother.setLastName("Keelmann");
        secondBrother.setBiologicalMotherId(2L);
        secondBrother.setBiologicalFatherId(3L);
        secondBrother.setSex("male");
        secondBrother.setDateOfBirth(LocalDate.of(1994, 8, 20));
        Person firstSisterFirstChild = new Person();
        firstSisterFirstChild.setId(14L);
        firstSisterFirstChild.setFirstName("Elisabeth");
        firstSisterFirstChild.setLastName("Lill");
        firstSisterFirstChild.setBiologicalMotherId(10L);
        firstSisterFirstChild.setSex("female");
        firstSisterFirstChild.setDateOfBirth(LocalDate.of(2008, 5, 20));
        Person firstSisterSecondChild = new Person();
        firstSisterSecondChild.setId(15L);
        firstSisterSecondChild.setFirstName("Katarina");
        firstSisterSecondChild.setLastName("Lill");
        firstSisterSecondChild.setBiologicalMotherId(10L);
        firstSisterSecondChild.setSex("female");
        firstSisterFirstChild.setDateOfBirth(LocalDate.of(2010, 4, 20));
        Person motherSideGrandMother = new Person();
        motherSideGrandMother.setId(16L);
        motherSideGrandMother.setFirstName("Heljo");
        motherSideGrandMother.setLastName("Igarik");
        motherSideGrandMother.setDateOfBirth(LocalDate.of(1930, 5, 6));
        Person motherSideGrandFather = new Person();
        motherSideGrandFather.setId(17L);
        motherSideGrandFather.setFirstName("Elmar");
        motherSideGrandFather.setLastName("Igarik");
        motherSideGrandFather.setDateOfBirth(LocalDate.of(1930, 5, 6));
        Person fatherSideGrandMother = new Person();
        fatherSideGrandMother.setId(18L);
        fatherSideGrandMother.setFirstName("Rea");
        fatherSideGrandMother.setLastName("Keelmann");
        fatherSideGrandMother.setDateOfBirth(LocalDate.of(1930, 5, 6));
        Person fatherSideGrandFather = new Person();
        fatherSideGrandFather.setId(19L);
        fatherSideGrandFather.setFirstName("Väino");
        fatherSideGrandFather.setLastName("Keelmann");
        fatherSideGrandFather.setDateOfBirth(LocalDate.of(1930, 5, 6));

        return asList(selectedPerson, mother, father, firstSister, secondSister, firstBrother, secondBrother, firstSisterFirstChild, firstSisterSecondChild, motherSideGrandMother, motherSideGrandFather, fatherSideGrandMother, fatherSideGrandFather);
    }
}
