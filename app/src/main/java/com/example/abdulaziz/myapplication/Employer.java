package com.example.abdulaziz.myapplication;



public class Employer {

     private String userName ;
    private String password ;
    private String RPname ;
    private String RPID ;
    private String RPphoneNum ;
    private int cityID ;
    private String googleMapLoc ;  //the URL of Google map location
    private String orgName ;
    private String orgDoc ;
    private String bio ;
    private String orgPic ;        //the URL of the pic



    public Employer(){

    }
    public Employer(  String userName, String password, String RPname, String RPID, String RPphoneNum, int cityID, String googleMapLoc, String orgName, String orgDoc, String bio, String orgPic) {
         this.userName = userName;
        this.password = password;
        this.RPname = RPname;
        this.RPID = RPID;
        this.RPphoneNum = RPphoneNum;
        this.cityID = cityID;
        this.googleMapLoc = googleMapLoc;
        this.orgName = orgName;
        this.orgDoc = orgDoc;
        this.bio = bio;
        this.orgPic = orgPic;
    }



    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRPname(String RPname) {
        this.RPname = RPname;
    }

    public void setRPID(String RPID) {
        this.RPID = RPID;
    }

    public void setRPphoneNum(String RPphoneNum) {
        this.RPphoneNum = RPphoneNum;
    }

    public void setCityID(int cityID) {
        this.cityID = cityID;
    }

    public void setGoogleMapLoc(String googleMapLoc) {
        this.googleMapLoc = googleMapLoc;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public void setOrgDoc(String orgDoc) {
        this.orgDoc = orgDoc;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setOrgPic(String orgPic) {
        this.orgPic = orgPic;
    }


    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getRPname() {
        return RPname;
    }

    public String getRPID() {
        return RPID;
    }

    public String getRPphoneNum() {
        return RPphoneNum;
    }

    public int getCityID() {
        return cityID;
    }

    public String getGoogleMapLoc() {
        return googleMapLoc;
    }

    public String getOrgName() {
        return orgName;
    }

    public String getOrgDoc() {
        return orgDoc;
    }

    public String getBio() {
        return bio;
    }

    public String getOrgPic() {
        return orgPic;
    }




}
