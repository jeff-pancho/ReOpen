package com.example.reopen;

public class LatLng {
    private double latitude;
    private double longitude;

    public LatLng () {

    }

    public LatLng(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }
}
