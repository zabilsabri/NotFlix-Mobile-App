package com.example.notflix.adapter;

import android.content.Intent;
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
import com.example.notflix.models.ModelsTv;

import java.util.List;

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.ViewHolder> {

    private List<ModelsTv> tv;

    public TvAdapter(List<ModelsTv> tv){
        this.tv = tv;
    }

    @NonNull
    @Override
    public TvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_movie, parent, false);
        return new TvAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvAdapter.ViewHolder holder, int position) {
        ModelsTv modelTv = tv.get(position);
        holder.tv_title.setText(modelTv.getName());
        Glide.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w500" + modelTv.getBackdrop_path()).into(holder.iv_poster);
        holder.tv_year.setText(modelTv.getFirstAirDate());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                intent.putExtra("synopsis", modelTv.getOverview());
                intent.putExtra("title", modelTv.getName());
                intent.putExtra("ratings", modelTv.getVote_average());
                intent.putExtra("backdrop", modelTv.getBackdrop_path());
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tv.size();
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
