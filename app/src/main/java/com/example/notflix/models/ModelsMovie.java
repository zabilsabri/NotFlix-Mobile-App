package com.example.notflix.models;

import com.google.gson.annotations.SerializedName;

public class ModelsMovie {
    @SerializedName("title")
    private String title;
    @SerializedName("poster_path")
    private String imagePoster;
    @SerializedName("release_date")
    private String ReleaseDate;
    @SerializedName("id")
    private int id;
    @SerializedName("backdrop_path")
    private String backdrop_path;
    @SerializedName("overview")
    private String overview;
    @SerializedName("vote_average")
    private String vote_average;

    public String getReleaseDate() {
        return ReleaseDate;
    }

    public String getTitle() {
        return title;
    }

    public String getImagePoster() {
        return imagePoster;
    }

    public int getId() {
        return id;
    }


    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getOverview() {
        return overview;
    }

    public String getVote_average() {
        return vote_average;
    }
}
