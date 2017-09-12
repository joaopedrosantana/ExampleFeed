package com.example.rosangela.examplefeed.Models;

/**
 * Created by MegaDev05 on 11/09/2017.
 */


public class Brewery {
    public String name;
    public String formattedAddress;
    public String photo;
    public float distance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public String getLogo() {
        return photo;
    }

    public void setLogo(String logo) {
        this.photo = photo;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
