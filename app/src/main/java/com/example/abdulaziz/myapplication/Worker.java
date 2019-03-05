package com.example.abdulaziz.myapplication;

import java.util.ArrayList;
import java.util.Date ;
import java.text.DateFormat ;

public class Worker {
    private String name;
    private String WorkerID;
    private int Workerstatus;
    private int price;
    private String Skills;
    private String picture;
    private String Nationality;
    private String Phonenumber;
    private String BirthDate;
    private String workerpdf;

    public Worker(){

    }
    public Worker(String name, String workerID, int price, String picture, String nationality, String Skills, String phonenumber, String birthDate,String workerpdf) {
        this.name = name;
        this.WorkerID = workerID;
        this.Workerstatus = 1; // 1 means avilable
        this.Skills = Skills;
        this.price = price;
        this.picture = picture;
        this.Nationality = nationality;
        this.Phonenumber = phonenumber;
        this.BirthDate = birthDate;
        this.workerpdf=workerpdf;
        //this.BirthDate  = DateFormat.getDateTimeInstance().format(new Date()) ;;

    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorkerID() {
        return WorkerID;
    }

    public void setWorkerID(String workerID) {
        WorkerID = workerID;
    }

    public int getWorkerstatus() {
        return Workerstatus;
    }

    public void setWorkerstatus(int workerstatus) {
        Workerstatus = workerstatus;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getNationality() {
        return Nationality;
    }

    public void setNationality(String nationality) {
        Nationality = nationality;
    }

    public String getPhonenumber() {
        return Phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        Phonenumber = phonenumber;
    }

    public String getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(String birthDate) {
        BirthDate = birthDate;
    }

}
