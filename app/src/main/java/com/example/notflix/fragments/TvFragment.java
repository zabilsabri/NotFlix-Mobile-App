package com.example.notflix.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.notflix.R;
import com.example.notflix.adapter.TvAdapter;
import com.example.notflix.api.ApiConfig;
import com.example.notflix.response.TvResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvFragment extends Fragment {
    private RecyclerView rv_tv;
    private TvAdapter tv_adapter;
    private ProgressBar pb_putar;
    private ImageView logo;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tv, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv_tv = view.findViewById(R.id.rv_movie);
        logo = view.findViewById(R.id.notflix_logo);
        pb_putar = view.findViewById(R.id.loading);

        rv_tv.setHasFixedSize(true);
        logo.setVisibility(View.VISIBLE);
        pb_putar.setVisibility(View.VISIBLE);
        ApiConfig.getApiService().getTvs(ApiConfig.getKey()).enqueue(new Callback<TvResponse>() {
            @Override
            public void onResponse(Call<TvResponse> call, Response<TvResponse> response) {
                if (response.isSuccessful() && response.body() != null){
                    logo.setVisibility(View.GONE);
                    pb_putar.setVisibility(View.GONE);
                    tv_adapter = new TvAdapter(response.body().getTvs());
                    rv_tv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                    rv_tv.setAdapter(tv_adapter);
                }
            }

            @Override
            public void onFailure(Call<TvResponse> call, Throwable t) {
                System.out.println("test");
            }
        });
    }
}