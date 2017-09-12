package com.example.rosangela.examplefeed.Services;

import com.example.rosangela.examplefeed.Models.BreweryCatalog;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by MegaDev05 on 11/09/2017.
 */

public interface BreweryService {

    public static final String BASE_URL = "http://braunetwork.megaleios.kinghost.net/api/v1/";

    @GET("Brewery/-23.557337/-46.660437/1")
    Call<BreweryCatalog> listBrewery();
}
