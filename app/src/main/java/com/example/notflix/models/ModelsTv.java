package com.example.notflix.models;

import com.google.gson.annotations.SerializedName;

public class ModelsTv {
    @SerializedName("name")
    private String name;
    @SerializedName("poster_path")
    private String imagePoster;
    @SerializedName("first_air_date")
    private String first_air_date;
    @SerializedName("id")
    private int id;
    @SerializedName("backdrop_path")
    private String backdrop_path;
    @SerializedName("overview")
    private String overview;
    @SerializedName("vote_average")
    private String vote_average;

    public String getFirstAirDate() {
        return first_air_date;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getImagePoster() {
        return imagePoster;
    }

    public String getOverview() {
        return overview;
    }

    public String getVote_average() {
        return vote_average;
    }
}
