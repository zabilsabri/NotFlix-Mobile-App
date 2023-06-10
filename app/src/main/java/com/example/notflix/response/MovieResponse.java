package com.example.notflix.response;

import com.example.notflix.models.ModelsMovie;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse {
    @SerializedName("results")
    private final List<ModelsMovie> movies;

    public MovieResponse(List<ModelsMovie> movies) {
        this.movies = movies;
    }

    public List<ModelsMovie> getMovies() {
        return movies;
    }
}
