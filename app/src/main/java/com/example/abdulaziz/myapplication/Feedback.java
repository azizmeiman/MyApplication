package com.example.abdulaziz.myapplication;

public class Feedback {

    private String workerID;
    private String Feddback;

    public Feedback(){

    }
    public Feedback(String workerID, String feddback) {
        this.workerID = workerID;
        this.Feddback = feddback;
    }

    public String getWorkerID() {
        return workerID;
    }

    public String getFeddback() {
        return Feddback;
    }

    public void setWorkerID(String workerID) {
        this.workerID = workerID;
    }

    public void setFeddback(String feddback) {
        Feddback = feddback;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "workerID='" + workerID + '\'' +
                ", Feddback='" + Feddback + '\'' +
                '}';
    }
}
