package com.example.abdulaziz.myapplication;



public class Employer {

    private String Email;
    private String RPname ;
    private String RPID ;
    private String RPphoneNum ;
    private String cityID ;
    private String orgName ;
    private String orgDoc ;
    private String orgPic ; //the URL of the pic


    private boolean isBlucked;


    private String IDE ;





    public Employer(){

    }

    public Employer(Employer value) {
        this.IDE=value.getIDE();
        this.Email = value.getEmail();
        this.RPname = value.getRPname();
        this.RPID = value.getRPID();
        this.RPphoneNum = value.getRPphoneNum();
        this.cityID = value.getCityID();
        this.orgName = value.getOrgName();
        this.orgDoc = value.getOrgDoc();
        this.orgPic = value.getOrgPic();
        this.isBlucked=value.isBlucked();

    }

    public Employer(String ide  ,String Email, String RPname, String RPID, String RPphoneNum, String cityID, String orgName, String orgDoc, String orgPic) {
        this.IDE=ide;
        this.Email = Email;
        this.RPname = RPname;
        this.RPID = RPID;
        this.RPphoneNum = RPphoneNum;
        this.cityID = cityID;
        this.orgName = orgName;
        this.orgDoc = orgDoc;
        this.orgPic = orgPic;
        this.isBlucked=false;

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


    public boolean isBlucked() {
        return isBlucked;
    }

    public void setBlucked(boolean blucked) {
        isBlucked = blucked;
    }

    public String getIDE() {
        return IDE;
    }

    public void setIDE(String IDE) {
        this.IDE = IDE;
    }


}
