package com.example.asheransari.uni;

/**
 * Created by asher.ansari on 9/23/2016.
 */
public class Customclass {

    private double magnitude;

    private String place;

    private long time;

    private String mUrl;



    public Customclass(final double magnitude, final String place, final long time, final String url){
        this.magnitude = magnitude;
        this.place = place;
        this.time = time;
        this.mUrl = url;
    }

    public String getmUrl() {
        return mUrl;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public String getPlace() {
        return place;
    }

    public long getTime() {
        return time;
    }






}
