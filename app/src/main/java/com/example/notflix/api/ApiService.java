package com.example.notflix.api;

import com.example.notflix.response.MovieResponse;
import com.example.notflix.response.TvResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("movie/now_playing?")
    Call<MovieResponse> getMovies (@Query("api_key") String key);

    @GET("tv/popular?")
    Call<TvResponse> getTvs (@Query("api_key") String key);
}
