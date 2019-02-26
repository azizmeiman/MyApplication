package com.example.abdulaziz.myapplication;



public class Employer {

    private String userName ;
    private String password ;
    private String RPname ;
    private String RPID ;
    private String RPphoneNum ;
    private String cityID ;
    private String orgName ;
    private String orgDoc ;
    private String orgPic ;        //the URL of the pic
    private int checkUser;



    public Employer(){

    }
    public Employer(  String userName, String password, String RPname, String RPID, String RPphoneNum, String cityID, String orgName, String orgDoc, String orgPic, int CheckUser) {
         this.userName = userName;
        this.password = password;
        this.RPname = RPname;
        this.RPID = RPID;
        this.RPphoneNum = RPphoneNum;
        this.cityID = cityID;
        this.orgName = orgName;
        this.orgDoc = orgDoc;
        this.orgPic = orgPic;
        this.checkUser = CheckUser;
    }

    public int getCheckUser() {
        return checkUser;
    }

    public void setCheckUser(int checkUser) {
        this.checkUser = checkUser;
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
