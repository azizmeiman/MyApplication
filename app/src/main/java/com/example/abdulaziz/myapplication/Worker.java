package com.example.abdulaziz.myapplication;

import java.util.ArrayList;
import java.util.Date ;
import java.text.DateFormat ;

public class Worker {
    private String name;
    private int WorkerID;
    private int Workerstatus;
    private String Bio;
    private ArrayList Skills;
    private int price;
    private String picture;
    private String Nationality;
    private String Phonenumber;
    private String BirthDate;
    private int CityID;
    private int WorkerPosterID;

    public Worker(String name, int workerID, int workerstatus, String bio, ArrayList skills, int price, String picture, String nationality, String phonenumber, DateFormat birthDate, int cityID, int workerPosterID) {
        this.name = name;
        WorkerID = workerID;
        Workerstatus = workerstatus;
        Bio = bio;
        Skills = skills;
        this.price = price;
        this.picture = picture;
        Nationality = nationality;
        Phonenumber = phonenumber;
        this.BirthDate  = DateFormat.getDateTimeInstance().format(new Date()) ;;
        CityID = cityID;
        WorkerPosterID = workerPosterID;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "name='" + name + '\'' +
                ", WorkerID=" + WorkerID +
                ", Workerstatus=" + Workerstatus +
                ", Bio='" + Bio + '\'' +
                ", Skills=" + Skills +
                ", price=" + price +
                ", picture='" + picture + '\'' +
                ", Nationality='" + Nationality + '\'' +
                ", Phonenumber='" + Phonenumber + '\'' +
                ", BirthDate='" + BirthDate + '\'' +
                ", CityID=" + CityID +
                ", WorkerPosterID=" + WorkerPosterID +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWorkerID() {
        return WorkerID;
    }

    public void setWorkerID(int workerID) {
        WorkerID = workerID;
    }

    public int getWorkerstatus() {
        return Workerstatus;
    }

    public void setWorkerstatus(int workerstatus) {
        Workerstatus = workerstatus;
    }

    public String getBio() {
        return Bio;
    }

    public void setBio(String bio) {
        Bio = bio;
    }

    public ArrayList getSkills() {
        return Skills;
    }

    public void setSkills(ArrayList skills) {
        Skills = skills;
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

    public int getCityID() {
        return CityID;
    }

    public void setCityID(int cityID) {
        CityID = cityID;
    }

    public int getWorkerPosterID() {
        return WorkerPosterID;
    }

    public void setWorkerPosterID(int workerPosterID) {
        WorkerPosterID = workerPosterID;
    }
}
