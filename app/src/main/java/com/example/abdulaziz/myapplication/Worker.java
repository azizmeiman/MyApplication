package com.example.abdulaziz.myapplication;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date ;
import java.text.DateFormat ;

public class Worker {

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
    private int TotalRate;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();


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
    }

    public Worker(String name, String workerID, int price, String picture, String nationality, String city, String Skills, String phonenumber, String birthDate,String PosterID, String Workerpdf, int Income, int Rate) {
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
        //this.BirthDate  = DateFormat.getDateTimeInstance().format(new Date()) ;;

    }

    public Worker(String name, String workerID, boolean isDeleted, int price, String picture, String nationality, String city, String Skills, String phonenumber, String birthDate,String PosterID, String Workerpdf, int Income,String id , boolean isAvailable, int Rate) {
        this.name = name;
        this.WorkerID = workerID;
        this.isDeleted = isDeleted; // 1 means avilable
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
        //this.BirthDate  = DateFormat.getDateTimeInstance().format(new Date()) ;;
        this.ID = id;
        this.isAvailable= isAvailable;
        this.TotalRate = Rate;
    }

    public int getTotalRate() {
        return TotalRate;
    }

    public void setTotalRate(int totalRate) {
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

    String endDate ;
    boolean busy = false;

    public String isAvailableMethod(){



        myRef.child("Contract").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Date current = new Date();

                for (DataSnapshot child : dataSnapshot.getChildren()){
                    Date  start = new Date(), end = new Date();

                    String sStart = child.child("startDate").getValue().toString();
                    String sEnd = child.child("endDate").getValue().toString();


                    @SuppressLint("SimpleDateFormat") SimpleDateFormat formatterS = new SimpleDateFormat("dd/MM/yyyy");

                    try {

                       start = formatterS.parse(sStart);


                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    @SuppressLint("SimpleDateFormat") SimpleDateFormat formatterE = new SimpleDateFormat("dd/MM/yyyy");

                    try {

                        end = formatterE.parse(sEnd);


                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    if(ID.equals(child.child("workerID").getValue().toString()) && end.after(current) && start.before(current)){

                        endDate = sEnd ;
                        busy = true;
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        if(busy){
            return endDate;
        }else{
            return "";
        }

    }

}
