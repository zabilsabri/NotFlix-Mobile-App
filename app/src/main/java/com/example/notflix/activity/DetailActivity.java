package com.example.notflix.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.notflix.R;
import com.example.notflix.database.DatabaseContract;
import com.example.notflix.database.MappingHelper;
import com.example.notflix.database.Notflix;
import com.example.notflix.database.NotflixHelper;

import java.lang.ref.WeakReference;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DetailActivity extends AppCompatActivity {

    private ImageView iv_backdrop, iv_poster, iv_logo;
    private TextView tv_sysnopsis, tv_title, tv_ratings, tv_category;
    private int id_item;
    private static String id_item_str, sysnopsis, title, ratings, poster, backdrop, date, category;
    private NotflixHelper notflixHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        notflixHelper = NotflixHelper.getInstance(getApplicationContext());

        iv_backdrop = findViewById(R.id.PictureBig);
        iv_poster = findViewById(R.id.PictureSmall);
        tv_title = findViewById(R.id.Title);
        tv_sysnopsis = findViewById(R.id.synopsis);
        tv_ratings = findViewById(R.id.Rating);
        iv_logo = findViewById(R.id.favorite);
        tv_category = findViewById(R.id.category);

        sysnopsis = getIntent().getStringExtra("synopsis");
        title = getIntent().getStringExtra("title");
        ratings = getIntent().getStringExtra("ratings");
        poster = getIntent().getStringExtra("poster");
        backdrop = getIntent().getStringExtra("backdrop");
        date = getIntent().getStringExtra("date");
        id_item = getIntent().getIntExtra("id_item", 0);
        category = getIntent().getStringExtra("category");
        id_item_str = Integer.toString(id_item);
        System.out.println(id_item_str);

        tv_title.setText(title);
        tv_category.setText(category);
        tv_ratings.setText("Ratings: " + ratings + "/10");
        tv_sysnopsis.setText(sysnopsis);
        Glide.with(DetailActivity.this).load("https://image.tmdb.org/t/p/w500" + poster).into(iv_poster);
        Glide.with(DetailActivity.this).load("https://image.tmdb.org/t/p/w500" + backdrop).into(iv_backdrop);

        ambilDatabase();
    }

    private void ambilDatabase(){
        new LoadNotflixAsync(this, notflixs -> {
            if (notflixs.size() == 0) {
                iv_logo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ContentValues values = new ContentValues();
                        values.put(DatabaseContract.NotflixColumns.TITLE, title);
                        values.put(DatabaseContract.NotflixColumns.RATINGS, ratings);
                        values.put(DatabaseContract.NotflixColumns.SINOPSIS, sysnopsis);
                        values.put(DatabaseContract.NotflixColumns.DATE, date);
                        values.put(DatabaseContract.NotflixColumns.BACKDROP, backdrop);
                        values.put(DatabaseContract.NotflixColumns.POSTER, poster);
                        values.put(DatabaseContract.NotflixColumns.ID_ITEM, id_item);
                        values.put(DatabaseContract.NotflixColumns.CATEGORY, category);
                        long result = notflixHelper.insert(values);
                        if (result > 0) {
                            Toast.makeText(DetailActivity.this, "Berhasil Menambahkan Favorite", Toast.LENGTH_SHORT).show();
                            ambilDatabase();
                        } else {
                            Toast.makeText(DetailActivity.this, "Failed to add data", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } else {
                iv_logo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        deleteFavorit();
                    }
                });
            }
        }).execute();
    }

    public void deleteFavorit(){
        long result = NotflixHelper.deleteById(String.valueOf(id_item));
        if (result > 0) {
            Toast.makeText(this, "Berhasil Menghapus Favorite", Toast.LENGTH_SHORT).show();
            ambilDatabase();
        } else {
            Toast.makeText(this, "Failed to delete data", Toast.LENGTH_SHORT).show();
        }
    }

    private static class LoadNotflixAsync {
        private final WeakReference<Context> weakContext;
        private final WeakReference<DetailActivity.LoadNotflixCallback> weakCallback;
        private LoadNotflixAsync(Context context, DetailActivity.LoadNotflixCallback callback) {
            weakContext = new WeakReference<>(context);
            weakCallback = new WeakReference<>(callback);
        }
        void execute() {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Handler handler = new Handler(Looper.getMainLooper());
            executor.execute(() -> {
                Context context = weakContext.get();
                NotflixHelper notflixHelper = NotflixHelper.getInstance(context);
                try {
                    notflixHelper.open();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                Cursor notflixCursor = notflixHelper.queryByNote(id_item_str);
                ArrayList<Notflix> notflixs = MappingHelper.mapCursorToArrayList(notflixCursor);
                handler.post(() -> weakCallback.get().postExecute(notflixs));
                System.out.println(notflixs.size());
            });
        }
    }

    interface LoadNotflixCallback {
        void postExecute(ArrayList<Notflix> notflixs);
    }

}