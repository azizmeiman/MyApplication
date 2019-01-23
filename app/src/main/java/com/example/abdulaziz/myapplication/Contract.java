package com.example.abdulaziz.myapplication;

import java.util.Date;

public class Contract {


    private int ContractID ;
    private int WorkerID ;
    private int empID ;
    private int PosterID ;
    private String Period ;
    private Date StartDate;
    private int Totalprice ;


    public Contract(){

    }
    public Contract(int contractID, int workerID, int empID, int posterID, String period, Date startDate, int totalprice) {

        ContractID = contractID;
        WorkerID = workerID;
        this.empID = empID;
        PosterID = posterID;
        Period = period;
        StartDate = startDate;
        Totalprice = totalprice;
    }
    public void setContractID(int contractID) {
        ContractID = contractID;
    }

    public void setWorkerID(int workerID) {
        WorkerID = workerID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public void setPosterID(int posterID) {
        PosterID = posterID;
    }

    public void setPeriod(String period) {
        Period = period;
    }

    public void setStartDate(Date startDate) {
        StartDate = startDate;
    }

    public void setTotalprice(int totalprice) {
        Totalprice = totalprice;
    }

    public int getContractID() {

        return ContractID;
    }

    public int getWorkerID() {
        return WorkerID;
    }

    public int getEmpID() {
        return empID;
    }

    public int getPosterID() {
        return PosterID;
    }

    public String getPeriod() {
        return Period;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public int getTotalprice() {
        return Totalprice;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "ContractID=" + ContractID +
                ", WorkerID=" + WorkerID +
                ", empID=" + empID +
                ", PosterID=" + PosterID +
                ", Period='" + Period + '\'' +
                ", StartDate=" + StartDate +
                ", Totalprice=" + Totalprice +
                '}';
    }
}
