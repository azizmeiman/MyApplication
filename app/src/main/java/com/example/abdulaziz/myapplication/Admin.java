package com.example.abdulaziz.myapplication;


public class Admin {
//asaadadada
    private int adminID;
    private String adminUserName;
    private String adminPassword;


    public Admin(int adminID, String admin_user_name, String admin_password) {
        adminID = adminID;
        adminUserName = admin_user_name;
        adminPassword = admin_password;
    }
    public Admin(){

    }


    public String getAdmin_user_name() {

        return adminUserName;
    }

    public void setAdmin_user_name(String adminUserName) {
        adminUserName = adminUserName;
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        adminID = adminID;
    }


    public String getAdmin_password() {
        return adminPassword;
    }

    public void setAdmin_password(String admin_password) {
        adminPassword = adminPassword;
    }


    @Override
    public String toString() {
        return "Admin{" +
                "AdminID=" + adminID +
                ", Admin_user_name='" + adminUserName + '\'' +
                ", Admin_password='" + adminPassword + '\'' +
                '}';
    }

}
