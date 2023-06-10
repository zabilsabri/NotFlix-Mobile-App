package com.example.notflix.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.notflix.R;
import com.example.notflix.adapter.MovieAdapter;
import com.example.notflix.api.ApiConfig;
import com.example.notflix.response.MovieResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieFragment extends Fragment {
    private RecyclerView rv_movies;
    private MovieAdapter movie_adapter;
    private ProgressBar pb_putar;
    private ImageView logo;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv_movies = view.findViewById(R.id.rv_movie);
        logo = view.findViewById(R.id.notflix_logo);
        pb_putar = view.findViewById(R.id.loading);

        rv_movies.setHasFixedSize(true);
        logo.setVisibility(View.VISIBLE);
        pb_putar.setVisibility(View.VISIBLE);
        ApiConfig.getApiService().getMovies(ApiConfig.getKey()).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful() && response.body() != null){
                    logo.setVisibility(View.GONE);
                    pb_putar.setVisibility(View.GONE);
                    movie_adapter = new MovieAdapter(response.body().getMovies());
                    rv_movies.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                    rv_movies.setAdapter(movie_adapter);
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                System.out.println("test");
            }
        });
    }
}