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
import com.example.notflix.database.Notflix;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder>{

    private List<Notflix> favorites;

    public FavoriteAdapter(List<Notflix> favorites){
        this.favorites = favorites;
    }

    @NonNull
    @Override
    public FavoriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_favorite, parent, false);
        return new FavoriteAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.ViewHolder holder, int position) {
        Notflix notflix = favorites.get(position);
        holder.tv_title.setText(notflix.getTitle());
        holder.tv_year.setText(notflix.getDate());
        holder.tv_ratings.setText("Ratings: " + notflix.getRatings());
        holder.tv_category.setText(notflix.getCategory());
        Glide.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w500" + notflix.getPoster()).into(holder.iv_poster);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                intent.putExtra("synopsis", notflix.getSinopsis());
                intent.putExtra("title", notflix.getTitle());
                intent.putExtra("ratings", notflix.getRatings());
                intent.putExtra("poster", notflix.getPoster());
                intent.putExtra("backdrop", notflix.getBackdrop());
                intent.putExtra("id_item", notflix.getId_item());
                intent.putExtra("date", notflix.getDate());
                intent.putExtra("category", notflix.getCategory());
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return favorites.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_poster;
        TextView tv_title, tv_year, tv_ratings, tv_category;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_poster = itemView.findViewById(R.id.image_poster);
            tv_title = itemView.findViewById(R.id.tv_title_f);
            tv_year = itemView.findViewById(R.id.release_f);
            tv_ratings = itemView.findViewById(R.id.rating_f);
            tv_category = itemView.findViewById(R.id.category_f);
        }
    }
}
