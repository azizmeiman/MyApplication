package com.example.abdulaziz.myapplication;

public class Admin {

    private int AdminID;
    private String Admin_user_name;
    private String Admin_password;

    @Override
    public String toString() {
        return "Admin{" +
                "AdminID=" + AdminID +
                ", Admin_user_name='" + Admin_user_name + '\'' +
                ", Admin_password='" + Admin_password + '\'' +
                '}';
    }

    public Admin(int adminID, String admin_user_name, String admin_password) {
        AdminID = adminID;
        Admin_user_name = admin_user_name;
        Admin_password = admin_password;
    }

    public String getAdmin_user_name() {

        return Admin_user_name;
    }

    public void setAdmin_user_name(String admin_user_name) {
        Admin_user_name = admin_user_name;
    }

    public int getAdminID() {
        return AdminID;
    }

    public void setAdminID(int adminID) {
        AdminID = adminID;
    }


    public String getAdmin_password() {
        return Admin_password;
    }

    public void setAdmin_password(String admin_password) {
        Admin_password = admin_password;
    }



}
