package com.example.abdulaziz.myapplication;

public class Request {



    private String contractID ;
    private String workerID ;
    private String empID ;
    private String posterID ;
    private String workerName ;
    private String empName ;
    private String EmpMobile;

    private int period ;
    private String startDate;
    private String endDate;
    private int totalprice ;
    private int status ; //1,Approved 2,Rejected 3,neither


    public Request(){

    }

    public Request(String contractID, String workerID, String empID, String mobilenum,String postermobile, String workerName, String empName, int period, String startDate, String endDate, int totalprice, int status) {
        this.contractID = contractID;
        this.workerID = workerID;
        this.empID = empID;
        this.posterID = posterID;
        this.EmpMobile = mobilenum;

        this.workerName = workerName;
        this.empName = empName;
        this.period = period;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalprice = totalprice;
        this.status = status;
    }



    public Request(Request r) {

        this.contractID = r.getContractID();
        this.workerID = r.getWorkerID();
        this.empID = r.getEmpID();
        this.posterID = r.getPosterID();
        this.EmpMobile = r.getEmpMobile();
        this.empName = r.getEmpName();
        this.workerName = r.getWorkerName();
        this.period = r.getPeriod();
        this.startDate = r.getStartDate();
        this.endDate = r.getEndDate();
        this.totalprice = r.getTotalprice();
        this.status = r.getStatus();
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpMobile() {
        return EmpMobile;
    }

    public void setEmpMobile(String empMobile) {
        EmpMobile = empMobile;
    }

    public String getWorkerName() {
        return workerName;
    }

    public String getEmpName() {
        return empName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getContractID() {
        return contractID;
    }

    public String getWorkerID() {
        return workerID;
    }

    public String getEmpID() {
        return empID;
    }

    public String getPosterID() {
        return posterID;
    }

    public int getPeriod() {
        return period;
    }

    public String getStartDate() {
        return startDate;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setContractID(String contractID) {
        this.contractID = contractID;
    }

    public void setWorkerID(String workerID) {
        this.workerID = workerID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public void setPosterID(String posterID) {
        this.posterID = posterID;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    @Override
    public String toString() {
        return "Request{" +
                "contractID='" + contractID + '\'' +
                ", workerID='" + workerID + '\'' +
                ", empID='" + empID + '\'' +
                ", posterID='" + posterID + '\'' +
                ", workerName='" + workerName + '\'' +
                ", empName='" + empName + '\'' +
                ", period=" + period +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", totalprice=" + totalprice +
                ", status=" + status +
                '}';
    }
}
