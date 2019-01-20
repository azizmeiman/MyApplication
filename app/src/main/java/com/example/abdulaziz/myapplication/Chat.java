package com.example.abdulaziz.myapplication;

import java.text.DateFormat;
import java.util.Date;

public class Chat {

    private int adminID ;
    private int WPID ;
    private int empID ;
    private int chatID ;
    private String message ;
    private String dateAndTime ;
    private String from ;
    private String to ;


    public Chat(int adminID, int WPID, int empID, int chatID, String message, String dateAndTime, String from, String to) {
        this.adminID = adminID;
        this.WPID = WPID;
        this.empID = empID;
        this.chatID = chatID;
        this.message = message;
        this.dateAndTime = DateFormat.getDateTimeInstance().format(new Date()) ;
        this.from = from;
        this.to = to;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public void setWPID(int WPID) {
        this.WPID = WPID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public void setChatID(int chatID) {
        this.chatID = chatID;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getAdminID() {
        return adminID;
    }

    public int getWPID() {
        return WPID;
    }

    public int getEmpID() {
        return empID;
    }

    public int getChatID() {
        return chatID;
    }

    public String getMessage() {
        return message;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "adminID=" + adminID +
                ", WPID=" + WPID +
                ", empID=" + empID +
                ", chatID=" + chatID +
                ", message='" + message + '\'' +
                ", dateAndTime='" + dateAndTime + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                '}';
    }
}
