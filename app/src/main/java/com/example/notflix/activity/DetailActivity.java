package com.example.notflix.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.notflix.R;

public class DetailActivity extends AppCompatActivity {

    private ImageView iv_backdrop, iv_poster;
    private TextView tv_sysnopsis, tv_title, tv_ratings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        iv_backdrop = findViewById(R.id.PictureBig);
        iv_poster = findViewById(R.id.PictureSmall);
        tv_title = findViewById(R.id.Title);
        tv_sysnopsis = findViewById(R.id.synopsis);
        tv_ratings = findViewById(R.id.Rating);

        String sysnopsis = getIntent().getStringExtra("synopsis");
        String title = getIntent().getStringExtra("title");
        String ratings = getIntent().getStringExtra("ratings");
        String poster = getIntent().getStringExtra("poster");
        String backdrop = getIntent().getStringExtra("backdrop");

        tv_title.setText(title);
        tv_ratings.setText(ratings);
        tv_sysnopsis.setText(sysnopsis);
        Glide.with(DetailActivity.this).load("https://image.tmdb.org/t/p/w500" + poster).into(iv_poster);
        Glide.with(DetailActivity.this).load("https://image.tmdb.org/t/p/w500" + backdrop).into(iv_backdrop);

    }
}