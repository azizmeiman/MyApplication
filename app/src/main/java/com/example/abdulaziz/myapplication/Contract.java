package com.example.abdulaziz.myapplication;

public class Contract {


    private String contractID ;
    private String workerID ;
    private String empID ;
    private String posterID ;
    private String workerName;
    private String empName;
    private int period ;
    private String startDate;
    private String endDate;
    private double totalprice ;
    private String EmployerMobile;
    private int status ; //1,Approved 2,Rejected 3,neither


    public Contract(){

    }

    public Contract(String contractID, String workerID, String empID, String posterID,String EmpMobile, String workerName, String empName,  int period, String startDate, String endDate, double totalprice, int status) {
        this.contractID = contractID;
        this.workerID = workerID;
        this.empID = empID;
        this.posterID = posterID;
        this.EmployerMobile = EmpMobile;
        this.workerName = workerName;
        this.empName = empName;
        this.period = period;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalprice = totalprice;
        this.status = status;
    }

    public Contract(Contract value) {
        this.contractID = value.getContractID();
        this.workerID = value.getWorkerID();
        this.empID = value.getEmpID();
        this.posterID = value.getPosterID();
        this.EmployerMobile = value.getEmployerMobile();
        this.workerName = value.getWorkerName();
        this.empName = value.getEmpName();
        this.period = value.getPeriod();
        this.startDate = value.getStartDate();
        this.endDate = value.getEndDate();
        this.totalprice = value.getTotalprice();
        this.status = value.getStatus();

    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }



    public String getWorkerName() {
        return workerName;
    }

    public String getEmpName() {
        return empName;
    }

    public String getEmployerMobile() {
        return EmployerMobile;
    }


    public void setEmployerMobile(String employerMobile) {
        EmployerMobile = employerMobile;
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

    public double getTotalprice() {
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

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "contractID=" + contractID +
                ", workerID=" + workerID +
                ", empID=" + empID +
                ", posterID=" + posterID +
                ", period='" + period + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", totalprice=" + totalprice +
                '}';
    }
}
