package ru.job4j.cinema.model;

public class Visitor {
    private String username;
    private String phone;
    private String place;

    public Visitor(String username, String phone, String place) {
        this.username = username;
        this.phone = phone;
        this.place = place;
    }

    public String getUsername() {
        return username;
    }

    public String getPhone() {
        return phone;
    }

    public String getPlace() {
        return place;
    }

}
