package com.klu.touristapi.model;

public class Tourist {
    private int id;
    private String name;
    private String country;
    private String passportNumber;

    public Tourist() {
    }

    public Tourist(int id, String name, String country, String passportNumber) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.passportNumber = passportNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }
}
