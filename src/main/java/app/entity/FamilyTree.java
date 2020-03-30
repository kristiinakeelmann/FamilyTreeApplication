package app.entity;

import lombok.Data;

import java.util.ArrayList;
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
    private List<String> children = new ArrayList<>();
    private List<String> sisters = new ArrayList<>();
    private List<String> brothers = new ArrayList<>();
    private List<String> aunts = new ArrayList<>();
    private List<String> uncles = new ArrayList<>();

}
