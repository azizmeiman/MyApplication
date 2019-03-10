package com.example.abdulaziz.myapplication;

import java.util.ArrayList;
import java.util.Date ;
import java.text.DateFormat ;

public class Worker {

    private String name;
    private String WorkerID;
    private String Workerstatus;
    private String price;
    private String Skills;
    private String picture;
    private String Nationality;
    private String Phonenumber;
    private String BirthDate;
    private String PosterID;
   private String Workerpdf;



    public Worker(){
    }
    public Worker(Worker w){
        this.name = w.getName();
        this.WorkerID = w.getWorkerID();
        this.Workerstatus = "1"; // 1 means avilable
        this.Skills = w.getSkills();
        this.price = w.getPrice();
        this.picture = w.getPicture();
        this.Nationality = w.getNationality();
        this.Phonenumber = w.getPhonenumber();
        this.BirthDate = w.getBirthDate();
        this.PosterID = w.getPosterID();
        this.Workerpdf = w.getWorkerpdf();
    }

    public Worker(String name, String workerID, String price, String picture, String nationality, String Skills, String phonenumber, String birthDate,String PosterID, String Workerpdf) {
        this.name = name;
        this.WorkerID = workerID;
        this.Workerstatus = "1"; // 1 means avilable
        this.Skills = Skills;
        this.price = price;
        this.picture = picture;
        this.Nationality = nationality;
        this.Phonenumber = phonenumber;
        this.BirthDate = birthDate;
        this.PosterID = PosterID;
        this.Workerpdf = Workerpdf;
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

    public String getWorkerstatus() {
        return Workerstatus;
    }

    public void setWorkerstatus(String workerstatus) {
        Workerstatus = workerstatus;
    }


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
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

    public String getSkills() {
        return Skills;
    }

    public void setSkills(String skills) {
        Skills = skills;
    }

    public String getPosterID() {
        return PosterID;
    }

    public void setPosterID(String posterID) {
        PosterID = posterID;
    }

    public String getWorkerpdf() {
        return Workerpdf;
    }

    public void setWorkerpdf(String workerpdf) {
        Workerpdf = workerpdf;
    }
}
