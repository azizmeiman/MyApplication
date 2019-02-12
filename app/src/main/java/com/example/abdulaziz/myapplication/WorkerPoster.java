package com.example.abdulaziz.myapplication;

import java.util.ArrayList;

public class WorkerPoster {


     private String username;
    private String password;
    private String RPname;
    private String RPID;
    private int RPphone ;
    private String cityID;
    private String googleMapLoc;
    private String orgName;
    private String orgPic;
    private String orgDoc;
    private String bio;
    private ArrayList workers;

    public WorkerPoster(){

    }
    public WorkerPoster(String username, String password, String RPname, String RPID, int RPphone, String cityID, String googleMapLoc, String orgName, String orgPic, String orgDoc, String bio, ArrayList workers) {
        this.username = username;
        this.password = password;
        this.RPname = RPname;
        this.RPID = RPID;
        this.RPphone = RPphone;
        this.cityID = cityID;
        this.googleMapLoc = googleMapLoc;
        this.orgName = orgName;
        this.orgPic = orgPic;
        this.orgDoc = orgDoc;
        this.bio = bio;
        this.workers = workers;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRPname() {
        return RPname;
    }

    public void setRPname(String RPname) {
        this.RPname = RPname;
    }

    public String getRPID() {
        return RPID;
    }

    public void setRPID(String RPID) {
        this.RPID = RPID;
    }

    public int getRPphone() {
        return RPphone;
    }

    public void setRPphone(int RPphone) {
        this.RPphone = RPphone;
    }

    public String getCityID() {
        return cityID;
    }

    public void setCityID(String cityID) {
        this.cityID = cityID;
    }

    public String getGoogleMapLoc() {
        return googleMapLoc;
    }

    public void setGoogleMapLoc(String googleMapLoc) {
        this.googleMapLoc = googleMapLoc;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgPic() {
        return orgPic;
    }

    public void setOrgPic(String orgPic) {
        this.orgPic = orgPic;
    }

    public String getOrgDoc() {
        return orgDoc;
    }

    public void setOrgDoc(String orgDoc) {
        this.orgDoc = orgDoc;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public ArrayList getWorkers() {
        return workers;
    }

    public void setWorkers(ArrayList workers) {
        this.workers = workers;
    }
}
