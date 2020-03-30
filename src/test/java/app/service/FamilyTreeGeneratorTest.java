package app.service;


import app.entity.FamilyTree;
import app.entity.Person;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static java.util.Arrays.asList;

public class FamilyTreeGeneratorTest {

    private FamilyTreeGenerator generator = new FamilyTreeGenerator();

    @Test
    public void create_noRelations() {

        Person person = new Person();
        person.setId(1L);
        person.setFirstName("Kristiina");
        person.setLastName("Keelmann");

        FamilyTree result = generator.create(asList(person), 1L);

        FamilyTree expected = new FamilyTree();
        expected.setSelectedPersonName("Kristiina Keelmann");
        Assert.assertEquals(expected, result);
    }


    @Test
    public void create_noRelations_twoPersons() {

        Person selectedPerson = new Person();
        selectedPerson.setId(1L);
        selectedPerson.setFirstName("Kristiina");
        selectedPerson.setLastName("Keelmann");
        Person notSelectedPerson = new Person();
        notSelectedPerson.setId(2L);
        notSelectedPerson.setFirstName("Mari");
        notSelectedPerson.setLastName("Kuusk");

        FamilyTree result = generator.create(asList(notSelectedPerson, selectedPerson), 1L);

        FamilyTree expected = new FamilyTree();
        expected.setSelectedPersonName("Kristiina Keelmann");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void create_relation_mother() {

        Person selectedPerson = new Person();
        selectedPerson.setId(1L);
        selectedPerson.setFirstName("Kristiina");
        selectedPerson.setLastName("Keelmann");
        selectedPerson.setBiologicalMotherId(2L);
        Person selectedPersonMother = new Person();
        selectedPersonMother.setId(2L);
        selectedPersonMother.setFirstName("Rutt");
        selectedPersonMother.setLastName("Keelmann");

        FamilyTree result = generator.create(asList(selectedPerson, selectedPersonMother), 1L);

        FamilyTree expected = new FamilyTree();
        expected.setSelectedPersonName("Kristiina Keelmann");
        expected.setMotherName("Rutt Keelmann");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void create_relation_father() {

        Person selectedPerson = new Person();
        selectedPerson.setId(1L);
        selectedPerson.setFirstName("Kristiina");
        selectedPerson.setLastName("Keelmann");
        selectedPerson.setBiologicalFatherId(2L);
        Person selectedPersonFather = new Person();
        selectedPersonFather.setId(2L);
        selectedPersonFather.setFirstName("Toivo");
        selectedPersonFather.setLastName("Keelmann");

        FamilyTree result = generator.create(asList(selectedPerson, selectedPersonFather), 1L);

        FamilyTree expected = new FamilyTree();
        expected.setSelectedPersonName("Kristiina Keelmann");
        expected.setFatherName("Toivo Keelmann");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void create_relation_motherMother() {

        Person selectedPerson = new Person();
        selectedPerson.setId(1L);
        selectedPerson.setFirstName("Kristiina");
        selectedPerson.setLastName("Keelmann");
        selectedPerson.setBiologicalMotherId(2L);
        Person mother = new Person();
        mother.setId(2L);
        mother.setFirstName("Rutt");
        mother.setLastName("Keelmann");
        mother.setBiologicalMotherId(3L);
        Person motherMother = new Person();
        motherMother.setId(3L);
        motherMother.setFirstName("Heljo");
        motherMother.setLastName("Igarik");

        FamilyTree result = generator.create(asList(selectedPerson, mother, motherMother), 1L);

        FamilyTree expected = new FamilyTree();
        expected.setSelectedPersonName("Kristiina Keelmann");
        expected.setMotherName("Rutt Keelmann");
        expected.setMotherMotherName("Heljo Igarik");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void create_relation_motherFather() {

        Person selectedPerson = new Person();
        selectedPerson.setId(1L);
        selectedPerson.setFirstName("Kristiina");
        selectedPerson.setLastName("Keelmann");
        selectedPerson.setBiologicalMotherId(2L);
        Person mother = new Person();
        mother.setId(2L);
        mother.setFirstName("Rutt");
        mother.setLastName("Keelmann");
        mother.setBiologicalFatherId(3L);
        Person motherFather = new Person();
        motherFather.setId(3L);
        motherFather.setFirstName("Elmar");
        motherFather.setLastName("Igarik");

        FamilyTree result = generator.create(asList(selectedPerson, mother, motherFather), 1L);

        FamilyTree expected = new FamilyTree();
        expected.setSelectedPersonName("Kristiina Keelmann");
        expected.setMotherName("Rutt Keelmann");
        expected.setMotherFatherName("Elmar Igarik");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void create_relation_fatherMother() {

        Person selectedPerson = new Person();
        selectedPerson.setId(1L);
        selectedPerson.setFirstName("Kristiina");
        selectedPerson.setLastName("Keelmann");
        selectedPerson.setBiologicalFatherId(2L);
        Person father = new Person();
        father.setId(2L);
        father.setFirstName("Toivo");
        father.setLastName("Keelmann");
        father.setBiologicalMotherId(3L);
        Person fatherMother = new Person();
        fatherMother.setId(3L);
        fatherMother.setFirstName("Rea");
        fatherMother.setLastName("Keelmann");

        FamilyTree result = generator.create(asList(selectedPerson, father, fatherMother), 1L);

        FamilyTree expected = new FamilyTree();
        expected.setSelectedPersonName("Kristiina Keelmann");
        expected.setFatherName("Toivo Keelmann");
        expected.setFatherMotherName("Rea Keelmann");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void create_relation_fatherFather() {

        Person selectedPerson = new Person();
        selectedPerson.setId(1L);
        selectedPerson.setFirstName("Kristiina");
        selectedPerson.setLastName("Keelmann");
        selectedPerson.setBiologicalFatherId(2L);
        Person father = new Person();
        father.setId(2L);
        father.setFirstName("Toivo");
        father.setLastName("Keelmann");
        father.setBiologicalFatherId(3L);
        Person fatherFather = new Person();
        fatherFather.setId(3L);
        fatherFather.setFirstName("Väino");
        fatherFather.setLastName("Keelmann");

        FamilyTree result = generator.create(asList(selectedPerson, father, fatherFather), 1L);

        FamilyTree expected = new FamilyTree();
        expected.setSelectedPersonName("Kristiina Keelmann");
        expected.setFatherName("Toivo Keelmann");
        expected.setFatherFatherName("Väino Keelmann");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void create_relation_motherHasOneChild() {

        Person selectedPerson = new Person();
        selectedPerson.setId(1L);
        selectedPerson.setFirstName("Rutt");
        selectedPerson.setLastName("Keelmann");
        Person child = new Person();
        child.setId(2L);
        child.setFirstName("Kristiina");
        child.setLastName("Keelmann");
        child.setBiologicalMotherId(1L);

        FamilyTree result = generator.create(asList(selectedPerson, child), 1L);

        FamilyTree expected = new FamilyTree();
        expected.setSelectedPersonName("Rutt Keelmann");
        expected.setChildren(Arrays.asList("Kristiina Keelmann"));
        Assert.assertEquals(expected, result);
    }

    @Test
    public void create_relation_fatherHasOneChild() {

        Person selectedPerson = new Person();
        selectedPerson.setId(1L);
        selectedPerson.setFirstName("Toivo");
        selectedPerson.setLastName("Keelmann");
        Person child = new Person();
        child.setId(2L);
        child.setFirstName("Kristiina");
        child.setLastName("Keelmann");
        child.setBiologicalFatherId(1L);

        FamilyTree result = generator.create(asList(selectedPerson, child), 1L);

        FamilyTree expected = new FamilyTree();
        expected.setSelectedPersonName("Toivo Keelmann");
        expected.setChildren(Arrays.asList("Kristiina Keelmann"));
        Assert.assertEquals(expected, result);
    }


    @Test
    public void create_relation_motherHasTwoChildren() {

        Person selectedPerson = new Person();
        selectedPerson.setId(1L);
        selectedPerson.setFirstName("Rutt");
        selectedPerson.setLastName("Keelmann");
        Person firstChild = new Person();
        firstChild.setId(2L);
        firstChild.setFirstName("Kristiina");
        firstChild.setLastName("Keelmann");
        firstChild.setBiologicalMotherId(1L);
        Person secondChild = new Person();
        secondChild.setId(3L);
        secondChild.setFirstName("Reigo");
        secondChild.setLastName("Keelmann");
        secondChild.setBiologicalMotherId(1L);

        FamilyTree result = generator.create(asList(selectedPerson, firstChild, secondChild), 1L);

        FamilyTree expected = new FamilyTree();
        expected.setSelectedPersonName("Rutt Keelmann");
        expected.setChildren(Arrays.asList("Kristiina Keelmann", "Reigo Keelmann"));
        Assert.assertEquals(expected, result);
    }

    @Test
    public void create_relation_sister() {

        Person selectedPerson = new Person();
        selectedPerson.setId(1L);
        selectedPerson.setFirstName("Kristiina");
        selectedPerson.setLastName("Keelmann");
        selectedPerson.setBiologicalMotherId(3L);
        Person sister = new Person();
        sister.setId(2L);
        sister.setFirstName("Krista");
        sister.setLastName("Keelmann");
        sister.setBiologicalMotherId(3L);
        sister.setSex("female");
        Person mother = new Person();
        mother.setId(3L);
        mother.setFirstName("Rutt");
        mother.setLastName("Keelmann");

        FamilyTree result = generator.create(asList(selectedPerson, mother, sister), 1L);

        FamilyTree expected = new FamilyTree();
        expected.setSelectedPersonName("Kristiina Keelmann");
        expected.setMotherName("Rutt Keelmann");
        expected.setSisters(Arrays.asList("Krista Keelmann"));
        Assert.assertEquals(expected, result);
    }

    @Test
    public void create_relation_sisters() {

        Person selectedPerson = new Person();
        selectedPerson.setId(1L);
        selectedPerson.setFirstName("Kristiina");
        selectedPerson.setLastName("Keelmann");
        selectedPerson.setBiologicalMotherId(3L);
        Person firstSister = new Person();
        firstSister.setId(2L);
        firstSister.setFirstName("Krista");
        firstSister.setLastName("Keelmann");
        firstSister.setBiologicalMotherId(3L);
        firstSister.setSex("female");
        Person secondSister = new Person();
        secondSister.setId(2L);
        secondSister.setFirstName("Ragne");
        secondSister.setLastName("Lill");
        secondSister.setBiologicalMotherId(3L);
        secondSister.setSex("female");
        Person mother = new Person();
        mother.setId(3L);
        mother.setFirstName("Rutt");
        mother.setLastName("Keelmann");

        FamilyTree result = generator.create(asList(selectedPerson, mother, firstSister, secondSister), 1L);

        FamilyTree expected = new FamilyTree();
        expected.setSelectedPersonName("Kristiina Keelmann");
        expected.setMotherName("Rutt Keelmann");
        expected.setSisters(Arrays.asList("Krista Keelmann", "Ragne Lill"));
        Assert.assertEquals(expected, result);
    }

    @Test
    public void create_relation_brother() {

        Person selectedPerson = new Person();
        selectedPerson.setId(1L);
        selectedPerson.setFirstName("Kristiina");
        selectedPerson.setLastName("Keelmann");
        selectedPerson.setBiologicalMotherId(3L);
        Person brother = new Person();
        brother.setId(2L);
        brother.setFirstName("Reigo");
        brother.setLastName("Keelmann");
        brother.setBiologicalMotherId(3L);
        brother.setSex("male");
        Person mother = new Person();
        mother.setId(3L);
        mother.setFirstName("Rutt");
        mother.setLastName("Keelmann");

        FamilyTree result = generator.create(asList(selectedPerson, mother, brother), 1L);

        FamilyTree expected = new FamilyTree();
        expected.setSelectedPersonName("Kristiina Keelmann");
        expected.setMotherName("Rutt Keelmann");
        expected.setBrothers(Arrays.asList("Reigo Keelmann"));
        Assert.assertEquals(expected, result);
    }

    @Test
    public void create_relation_fatherSideAunt() {

        Person selectedPerson = new Person();
        selectedPerson.setId(1L);
        selectedPerson.setFirstName("Kristiina");
        selectedPerson.setLastName("Keelmann");
        selectedPerson.setBiologicalFatherId(2L);
        Person father = new Person();
        father.setId(2L);
        father.setFirstName("Toivo");
        father.setLastName("Keelmann");
        father.setBiologicalMotherId(4L);
        Person aunt = new Person();
        aunt.setId(3L);
        aunt.setFirstName("Eha");
        aunt.setLastName("Kummer");
        aunt.setBiologicalMotherId(4L);
        aunt.setSex("female");
        Person fatherMother = new Person();
        fatherMother.setId(4L);
        fatherMother.setFirstName("Rea");
        fatherMother.setLastName("Keelmann");

        FamilyTree result = generator.create(asList(selectedPerson, father, aunt, fatherMother), 1L);

        FamilyTree expected = new FamilyTree();
        expected.setSelectedPersonName("Kristiina Keelmann");
        expected.setFatherName("Toivo Keelmann");
        expected.setAunts(Arrays.asList("Eha Kummer"));
        expected.setFatherMotherName("Rea Keelmann");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void create_relation_motherSideAunt() {

        Person selectedPerson = new Person();
        selectedPerson.setId(1L);
        selectedPerson.setFirstName("Kristiina");
        selectedPerson.setLastName("Keelmann");
        selectedPerson.setBiologicalMotherId(2L);
        Person mother = new Person();
        mother.setId(2L);
        mother.setFirstName("Rutt");
        mother.setLastName("Keelmann");
        mother.setBiologicalMotherId(4L);
        Person aunt = new Person();
        aunt.setId(3L);
        aunt.setFirstName("Säde");
        aunt.setLastName("Igarik");
        aunt.setBiologicalMotherId(4L);
        aunt.setSex("female");
        Person motherMother = new Person();
        motherMother.setId(4L);
        motherMother.setFirstName("Heljo");
        motherMother.setLastName("Igarik");

        FamilyTree result = generator.create(asList(selectedPerson, mother, aunt, motherMother), 1L);

        FamilyTree expected = new FamilyTree();
        expected.setSelectedPersonName("Kristiina Keelmann");
        expected.setMotherName("Rutt Keelmann");
        expected.setAunts(Arrays.asList("Säde Igarik"));
        expected.setMotherMotherName("Heljo Igarik");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void create_relation_fatherSideUncle() {

        Person selectedPerson = new Person();
        selectedPerson.setId(1L);
        selectedPerson.setFirstName("Kristiina");
        selectedPerson.setLastName("Keelmann");
        selectedPerson.setBiologicalFatherId(2L);
        Person father = new Person();
        father.setId(2L);
        father.setFirstName("Toivo");
        father.setLastName("Keelmann");
        father.setBiologicalMotherId(4L);
        Person uncle = new Person();
        uncle.setId(3L);
        uncle.setFirstName("Torm");
        uncle.setLastName("Keelmann");
        uncle.setBiologicalMotherId(4L);
        uncle.setSex("male");
        Person fatherMother = new Person();
        fatherMother.setId(4L);
        fatherMother.setFirstName("Rea");
        fatherMother.setLastName("Keelmann");

        FamilyTree result = generator.create(asList(selectedPerson, father, uncle, fatherMother), 1L);

        FamilyTree expected = new FamilyTree();
        expected.setSelectedPersonName("Kristiina Keelmann");
        expected.setFatherName("Toivo Keelmann");
        expected.setUncles(Arrays.asList("Torm Keelmann"));
        expected.setFatherMotherName("Rea Keelmann");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void create_relation_motherSideUncle() {

        Person selectedPerson = new Person();
        selectedPerson.setId(1L);
        selectedPerson.setFirstName("Kristiina");
        selectedPerson.setLastName("Keelmann");
        selectedPerson.setBiologicalMotherId(2L);
        Person mother = new Person();
        mother.setId(2L);
        mother.setFirstName("Rutt");
        mother.setLastName("Keelmann");
        mother.setBiologicalMotherId(4L);
        Person uncle = new Person();
        uncle.setId(3L);
        uncle.setFirstName("Maru");
        uncle.setLastName("Igarik");
        uncle.setBiologicalMotherId(4L);
        uncle.setSex("male");
        Person motherMother = new Person();
        motherMother.setId(4L);
        motherMother.setFirstName("Heljo");
        motherMother.setLastName("Igarik");

        FamilyTree result = generator.create(asList(selectedPerson, mother, uncle, motherMother), 1L);

        FamilyTree expected = new FamilyTree();
        expected.setSelectedPersonName("Kristiina Keelmann");
        expected.setMotherName("Rutt Keelmann");
        expected.setUncles(Arrays.asList("Maru Igarik"));
        expected.setMotherMotherName("Heljo Igarik");
        Assert.assertEquals(expected, result);
    }

    @Test
    public void create_relation_fullFamily() {

        Person selectedPerson = new Person();
        selectedPerson.setId(1L);
        selectedPerson.setFirstName("Kristiina");
        selectedPerson.setLastName("Keelmann");
        selectedPerson.setBiologicalMotherId(2L);
        selectedPerson.setBiologicalFatherId(3L);
        Person mother = new Person();
        mother.setId(2L);
        mother.setFirstName("Rutt");
        mother.setLastName("Keelmann");
        mother.setBiologicalMotherId(4L);
        Person father = new Person();
        father.setId(3L);
        father.setFirstName("Toivo");
        father.setLastName("Keelmann");
        father.setBiologicalMotherId(5L);
        Person firstSister = new Person();
        firstSister.setId(10L);
        firstSister.setFirstName("Ragne");
        firstSister.setLastName("Lill");
        firstSister.setBiologicalMotherId(2L);
        firstSister.setSex("female");
        Person secondSister = new Person();
        secondSister.setId(11L);
        secondSister.setFirstName("Krista");
        secondSister.setLastName("Keelmann");
        secondSister.setBiologicalMotherId(2L);
        secondSister.setSex("female");
        Person firstBrother = new Person();
        firstBrother.setId(12L);
        firstBrother.setFirstName("Kaspar");
        firstBrother.setLastName("Keelmann");
        firstBrother.setBiologicalMotherId(2L);
        firstBrother.setSex("male");
        Person secondBrother = new Person();
        secondBrother.setId(13L);
        secondBrother.setFirstName("Reigo");
        secondBrother.setLastName("Keelmann");
        secondBrother.setBiologicalMotherId(2L);
        secondBrother.setSex("male");
        Person motherMother = new Person();
        motherMother.setId(4L);
        motherMother.setFirstName("Heljo");
        motherMother.setLastName("Igarik");
        Person fatherMother = new Person();
        fatherMother.setId(5L);
        fatherMother.setFirstName("Rea");
        fatherMother.setLastName("Keelmann");
        Person motherSideuncle = new Person();
        motherSideuncle.setId(6L);
        motherSideuncle.setFirstName("Maru");
        motherSideuncle.setLastName("Igarik");
        motherSideuncle.setBiologicalMotherId(4L);
        motherSideuncle.setSex("male");
        Person fatherSideuncle = new Person();
        fatherSideuncle.setId(6L);
        fatherSideuncle.setFirstName("Torm");
        fatherSideuncle.setLastName("Keelmann");
        fatherSideuncle.setBiologicalMotherId(5L);
        fatherSideuncle.setSex("male");
        Person motherSideaunt = new Person();
        motherSideaunt.setId(7L);
        motherSideaunt.setFirstName("Säde");
        motherSideaunt.setLastName("Igarik");
        motherSideaunt.setBiologicalMotherId(4L);
        motherSideaunt.setSex("female");
        Person firstFatherSideaunt = new Person();
        firstFatherSideaunt.setId(8L);
        firstFatherSideaunt.setFirstName("Aimi");
        firstFatherSideaunt.setLastName("Täht");
        firstFatherSideaunt.setBiologicalMotherId(5L);
        firstFatherSideaunt.setSex("female");
        Person secondFatherSideaunt = new Person();
        secondFatherSideaunt.setId(9L);
        secondFatherSideaunt.setFirstName("Eha");
        secondFatherSideaunt.setLastName("Kummer");
        secondFatherSideaunt.setBiologicalMotherId(5L);
        secondFatherSideaunt.setSex("female");

        FamilyTree result = generator.create(asList(selectedPerson, mother, father, firstSister, secondSister, firstBrother, secondBrother, motherMother, fatherMother, motherSideuncle, fatherSideuncle, motherSideaunt, firstFatherSideaunt, secondFatherSideaunt), 1L);

        FamilyTree expected = new FamilyTree();
        expected.setSelectedPersonName("Kristiina Keelmann");
        expected.setMotherName("Rutt Keelmann");
        expected.setFatherName("Toivo Keelmann");
        expected.setSisters(Arrays.asList("Ragne Lill", "Krista Keelmann"));
        expected.setBrothers(Arrays.asList("Kaspar Keelmann", "Reigo Keelmann"));
        expected.setUncles(Arrays.asList("Maru Igarik", "Torm Keelmann"));
        expected.setAunts(Arrays.asList("Säde Igarik", "Aimi Täht", "Eha Kummer"));
        expected.setMotherMotherName("Heljo Igarik");
        expected.setFatherMotherName("Rea Keelmann");
        Assert.assertEquals(expected, result);
    }


}