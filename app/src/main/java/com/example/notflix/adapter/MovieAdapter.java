package com.example.notflix.adapter;

import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.notflix.R;
import com.example.notflix.activity.DetailActivity;
import com.example.notflix.models.ModelsMovie;
import com.example.notflix.response.MovieResponse;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<ModelsMovie> movies;

    public MovieAdapter(List<ModelsMovie> movies){
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModelsMovie modelMovie = movies.get(position);
        holder.tv_title.setText(modelMovie.getTitle());
        Glide.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w500" + modelMovie.getImagePoster()).into(holder.iv_poster);
        holder.tv_year.setText(modelMovie.getReleaseDate());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                intent.putExtra("synopsis", modelMovie.getOverview());
                intent.putExtra("title", modelMovie.getTitle());
                intent.putExtra("ratings", modelMovie.getVote_average());
                intent.putExtra("poster", modelMovie.getImagePoster());
                intent.putExtra("backdrop", modelMovie.getBackdrop_path());
                intent.putExtra("id_item", modelMovie.getId());
                intent.putExtra("date", modelMovie.getReleaseDate());
                intent.putExtra("category", "Movie");
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_poster;
        TextView tv_title, tv_year;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_poster = itemView.findViewById(R.id.mv_poster);
            tv_title = itemView.findViewById(R.id.mv_title);
            tv_year = itemView.findViewById(R.id.mv_release);
        }
    }
}
