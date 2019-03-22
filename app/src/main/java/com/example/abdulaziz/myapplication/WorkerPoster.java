package com.example.abdulaziz.myapplication;

import java.util.ArrayList;

public class WorkerPoster {


    private String Email;
    private String RPname;
    private String RPID;
    private String RPphone ;
    private String cityID;
    private String orgName;
    private String orgPic;
    private String orgDoc;

    private ArrayList workers;

    private double systemfees;
    private String IDP;
    private boolean isBlucked;

    private String FeesRelesPic;

    public WorkerPoster(){

    }

    public WorkerPoster(String idp,String Email, String RPname, String RPID, String RPphone, String cityID, String orgName, String orgPic, String orgDoc) {
        this.IDP=idp;
        this.Email=Email;
        this.RPname = RPname;
        this.RPID = RPID;
        this.RPphone = RPphone;
        this.cityID = cityID;
        this.orgName = orgName;
        this.orgPic = orgPic;
        this.orgDoc = orgDoc;
        this.workers = workers;
        this.FeesRelesPic="empty";
        this.isBlucked=false;

    }

    public WorkerPoster(WorkerPoster value) {
        this.IDP=value.getIDP();


        this.Email = value.getEmail();
        this.RPname = value.getRPname();
        this.RPID = value.getRPID();
        this.RPphone = value.getRPphone();
        this.cityID = value.getCityID();
        this.orgName = value.getOrgName();
        this.orgDoc = value.getOrgDoc();
        this.orgPic = value.getOrgPic();
        this.FeesRelesPic=value.getFeesRelesPic();
        this.isBlucked=value.isBlucked();
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
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

    public String getRPphone() {
        return RPphone;
    }

    public void setRPphone(String RPphone) {
        this.RPphone = RPphone;
    }

    public String getCityID() {
        return cityID;
    }

    public void setCityID(String cityID) {
        this.cityID = cityID;
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

    public ArrayList getWorkers() {
        return workers;
    }

    public void setWorkers(ArrayList workers) {
        this.workers = workers;
    }

    public double getSystemfees() {
        return systemfees;
    }

    public void setSystemfees(double systemfees) {
        this.systemfees = systemfees;
    }

    public String getFeesRelesPic() {
        return FeesRelesPic;
    }

    public void setFeesRelesPic(String feesRelesPic) {
        FeesRelesPic = feesRelesPic;
    }


    public boolean isBlucked() {
        return isBlucked;
    }

    public void setBlucked(boolean blucked) {
        isBlucked = blucked;
    }


    public String getIDP() {
        return IDP;
    }

    public void setIDP(String IDP) {
        this.IDP = IDP;
    }

}

