package com.example.abdulaziz.myapplication;



public class Employer {

    private String Email;
    private String RPname ;
    private String RPID ;
    private String RPphoneNum ;
    private String cityID ;
    private String orgName ;
    private String orgDoc ;
    private String orgPic ;        //the URL of the pic




    public Employer(){

    }
    public Employer(  String Email, String RPname, String RPID, String RPphoneNum, String cityID, String orgName, String orgDoc, String orgPic) {
        this.Email = Email;
        this.RPname = RPname;
        this.RPID = RPID;
        this.RPphoneNum = RPphoneNum;
        this.cityID = cityID;
        this.orgName = orgName;
        this.orgDoc = orgDoc;
        this.orgPic = orgPic;

    }


    public void setEmail(String email) {
        this.Email = email;
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

    public void setCityID(String cityID) {
        this.cityID = cityID;
    }



    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public void setOrgDoc(String orgDoc) {
        this.orgDoc = orgDoc;
    }



    public void setOrgPic(String orgPic) {
        this.orgPic = orgPic;
    }


    public String getEmail() {
        return Email;
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

    public String getCityID() {
        return cityID;
    }



    public String getOrgName() {
        return orgName;
    }

    public String getOrgDoc() {
        return orgDoc;
    }



    public String getOrgPic() {
        return orgPic;
    }




}
