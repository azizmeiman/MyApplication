package com.example.abdulaziz.myapplication;

public class Skill {

    private String skillName;
    private String skillID;

    public Skill(){

    }
    public Skill(String name, String ID) {
        this.skillName = name;
        this.skillID = ID;
    }

    public String getSkillName() {
        return skillName;
    }

    public String getID() {
        return skillID;
    }

    public void setName(String name) {
        this.skillName = name;
    }

    public void setID(String ID) {
        this.skillID = ID;
    }

}
