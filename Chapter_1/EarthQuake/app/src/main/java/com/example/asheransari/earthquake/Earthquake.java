package com.example.asheransari.earthquake;

/**
 * Created by asher.ansari on 9/23/2016.
 */
public class Earthquake {
    private String magnitude;
    private String date;
    private String place;
    public Earthquake(String mag, String plac, String dateA)
    {
        this.magnitude = mag;
        this.place = plac;
        this.date = dateA;
    }

    public String getMagnitude() {
        return magnitude;
    }

    public String getDate() {
        return date;
    }

    public String getPlace() {
        return place;
    }
}
