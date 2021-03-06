package com.example.assignment12;

public class FavouritePlaceClass {

    int id;
    String address;
    Double latitude, longitude;
    String date;

    public FavouritePlaceClass(int id, String address, Double latitude, Double longitude, String date) {
        this.id = id;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getLatitude() {
        return String.valueOf(latitude);
    }

    public String getLongitude() {
        return String.valueOf(longitude);
    }

    public String getDate() {
        return date;
    }
}
