package com.example.notflix.response;

import com.example.notflix.models.ModelsMovie;
import com.example.notflix.models.ModelsTv;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvResponse {
    @SerializedName("results")
    private final List<ModelsTv> movies;

    public TvResponse(List<ModelsTv> movies) {
        this.movies = movies;
    }

    public List<ModelsTv> getTvs() {
        return movies;
    }
}
