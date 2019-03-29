package com.example.abdulaziz.myapplication;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date ;
import java.text.DateFormat ;

public class Worker implements Serializable {

    private String name;
    private String WorkerID;
    private boolean isDeleted;
    private int price;
    private String Skills;
    private String picture;
    private String Nationality;
    private String City;
    private String Phonenumber;
    private String BirthDate;
    private String PosterID;
    private String Workerpdf;
    private int TotalIncome;
    private String ID;
    private boolean isAvailable;
    private float TotalRate;
    private int nRate;//counter
    private String until;



    public Worker(){
    }
    public Worker(Worker w){
        this.name = w.getName();
        this.WorkerID = w.getWorkerID();
        this.isDeleted = false;
        this.Skills = w.getSkills();
        this.price = w.getPrice();
        this.picture = w.getPicture();
        this.Nationality = w.getNationality();
        this.City = w.getCity();
        this.Phonenumber = w.getPhonenumber();
        this.BirthDate = w.getBirthDate();
        this.PosterID = w.getPosterID();
        this.Workerpdf = w.getWorkerpdf();
        this.TotalIncome = w.getTotalIncome();
        this.ID = w.getID();
        this.isAvailable= w.isAvailable();
        this.TotalRate = w.getTotalRate();
        this.nRate = w.getnRate();
    }

    public Worker(String name, String workerID, int price, String picture, String nationality, String city, String Skills, String phonenumber, String birthDate,String PosterID, String Workerpdf, int Income, float Rate, int nRate) {
        this.name = name;
        this.WorkerID = workerID;
        this.isDeleted = false; // 1 means avilable
        this.Skills = Skills;
        this.price = price;
        this.picture = picture;
        this.Nationality = nationality;
        this.City = city;
        this.Phonenumber = phonenumber;
        this.BirthDate = birthDate;
        this.PosterID = PosterID;
        this.Workerpdf = Workerpdf;
        this.TotalIncome = Income;
        this.TotalRate = Rate;
        this.nRate = nRate;
    }

    public Worker(String name, String workerID, boolean isDeleted, int price, String picture, String nationality, String city, String Skills, String phonenumber, String birthDate,String PosterID, String Workerpdf, int Income,String id , boolean isAvailable, float Rate, int nRate, String until) {
        this.name = name;
        this.WorkerID = workerID;
        this.isDeleted = isDeleted;
        this.Skills = Skills;
        this.price = price;
        this.picture = picture;
        this.Nationality = nationality;
        this.City = city;
        this.Phonenumber = phonenumber;
        this.BirthDate = birthDate;
        this.PosterID = PosterID;
        this.Workerpdf = Workerpdf;
        this.TotalIncome = Income;
        this.ID = id;
        this.isAvailable= isAvailable;
        this.TotalRate = Rate;
        this.nRate = nRate;
        this.until = until;
    }

    public String getUntil() {
        return until;
    }

    public void setUntil(String until) {
        this.until = until;
    }

    public int getnRate() {
        return nRate;
    }

    public void setnRate(int nRate) {
        this.nRate = nRate;
    }

    public float getTotalRate() {
        return TotalRate;
    }

    public void setTotalRate(float totalRate) {
        this.TotalRate = totalRate;
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

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
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

    public int getTotalIncome() {
        return TotalIncome;
    }

    public void setTotalIncome(int totalIncome) {
        TotalIncome = TotalIncome+totalIncome;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getNationality() {
        return Nationality;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
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

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }





}
