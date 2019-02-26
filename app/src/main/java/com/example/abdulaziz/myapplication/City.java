package com.example.abdulaziz.myapplication;

public class City {

    private String cityName ;

    public City(){

    }
    public City(String name) {
        this.cityName = name;

    }

    public String getName() {
        return cityName;
    }



    public void setName(String name) {
        this.cityName = name;
    }

    @Override
    public String toString() {
        return "City{" +
                "cityName='" + cityName + '\'' +
                '}';
    }
}
