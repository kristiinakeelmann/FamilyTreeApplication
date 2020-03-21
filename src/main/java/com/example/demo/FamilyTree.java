package com.example.demo;

import lombok.Data;

import java.util.List;

@Data
public class FamilyTree {

    private String selectedPersonName;
    private String motherName;
    private String fatherName;
    private String motherMotherName;
    private String motherFatherName;
    private String fatherMotherName;
    private String fatherFatherName;
    private List<String> children;
    private List<String> sisters;
    private List<String> brothers;
    private List<String> aunts;
    private List<String> uncles;

}
