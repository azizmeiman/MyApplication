package com.example.abdulaziz.myapplication;

public class City {

    private String cityName ;
    private String cityID ;

    public City(String name, String ID) {
        this.cityName = name;
        this.cityID = ID;
    }

    public String getName() {
        return cityName;
    }

    public String getID() {
        return cityID;
    }

    public void setName(String name) {
        this.cityName = name;
    }

    public void setID(String ID) {
        this.cityID = ID;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + cityName + '\'' +
                ", ID='" + cityID + '\'' +
                '}';
    }
}
