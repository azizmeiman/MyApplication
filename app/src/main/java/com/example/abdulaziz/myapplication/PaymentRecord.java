package com.example.abdulaziz.myapplication;

public class PaymentRecord {


    private String pic ;
    private String posterID ;
    private double AmountOfPayment;

    public PaymentRecord(){


    }

    public PaymentRecord(PaymentRecord p){
this.pic=p.getPic();
this.posterID=p.posterID;
this.AmountOfPayment=p.getAmountOfPayment();

    }

    public PaymentRecord(String pic, String posterID, double amountOfPayment) {
        this.pic = pic;
        this.posterID = posterID;
        AmountOfPayment = amountOfPayment;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPosterID() {
        return posterID;
    }

    public void setPosterID(String posterID) {
        this.posterID = posterID;
    }

    public double getAmountOfPayment() {
        return AmountOfPayment;
    }

    public void setAmountOfPayment(int amountOfPayment) {
        AmountOfPayment = amountOfPayment;
    }
}
