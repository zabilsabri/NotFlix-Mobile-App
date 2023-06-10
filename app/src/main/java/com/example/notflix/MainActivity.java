package com.example.notflix;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private ImageButton ib_movie, ib_tv, ib_favorite;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ib_movie = findViewById(R.id.movieButton);
        ib_tv = findViewById(R.id.tvButton);
        ib_favorite = findViewById(R.id.favoriteButton);

        fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(MovieFragment.class.getSimpleName());

        if (!(fragment instanceof MovieFragment)){
            pindahFragment(new MovieFragment());
        }

        ib_movie.setOnClickListener(view -> {
            pindahFragment(new MovieFragment());
        });

        ib_tv.setOnClickListener(view -> {
            pindahFragment(new TvFragment());
        });

        ib_favorite.setOnClickListener(view -> {
            pindahFragment(new FavoriteFragment());
        });

    }

    private void pindahFragment(Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.frame_container, fragment, fragment.getClass().getSimpleName())
                .commit();
    }
}