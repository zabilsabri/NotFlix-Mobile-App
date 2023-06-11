package com.example.notflix.fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.notflix.R;
import com.example.notflix.adapter.FavoriteAdapter;
import com.example.notflix.adapter.MovieAdapter;
import com.example.notflix.api.ApiConfig;
import com.example.notflix.database.MappingHelper;
import com.example.notflix.database.Notflix;
import com.example.notflix.database.NotflixHelper;
import com.example.notflix.response.MovieResponse;

import java.lang.ref.WeakReference;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoriteFragment extends Fragment {
    private RecyclerView rv_favorites;
    private FavoriteAdapter favorite_adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv_favorites = view.findViewById(R.id.rv_movie);

        rv_favorites.setHasFixedSize(true);

        new LoadNotflixAsync(getContext(), notflixs -> {
            if (notflixs.size() > 0) {
                rv_favorites.setLayoutManager(new LinearLayoutManager(getContext()));
                favorite_adapter = new FavoriteAdapter(notflixs);
                rv_favorites.setAdapter(favorite_adapter);
            } else {
                System.out.println("Buset");
            }
        }).execute();

    }

    private static class LoadNotflixAsync {
        private final WeakReference<Context> weakContext;
        private final WeakReference<LoadNotflixCallback> weakCallback;
        private LoadNotflixAsync(Context context, LoadNotflixCallback callback) {
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
                Cursor notflixCursor = notflixHelper.queryAll();
                ArrayList<Notflix> notflixs =
                        MappingHelper.mapCursorToArrayList(notflixCursor);
                handler.post(() -> weakCallback.get().postExecute(notflixs));
            });
        }
    }

    interface LoadNotflixCallback {
        void postExecute(ArrayList<Notflix> notflix);
    }

}