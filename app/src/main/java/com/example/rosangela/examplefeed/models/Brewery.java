package com.example.rosangela.examplefeed.models;

/**
 * Created by MegaDev05 on 11/09/2017.
 */


public class Brewery {
    public String formattedAddress, photo, name, logo, id;
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
        return logo;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
