package com.example.notflix.database;

import android.database.Cursor;

import java.util.ArrayList;

public class MappingHelper {
    public static ArrayList<Notflix> mapCursorToArrayList(Cursor cursor) {
        ArrayList<Notflix> notflixs = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.NotflixColumns._ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.NotflixColumns.TITLE));
            String ratings = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.NotflixColumns.RATINGS));
            String sinopsis = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.NotflixColumns.SINOPSIS));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.NotflixColumns.DATE));
            String backdrop = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.NotflixColumns.BACKDROP));
            String poster = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.NotflixColumns.POSTER));
            String id_item = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.NotflixColumns.ID_ITEM));
            String category = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.NotflixColumns.CATEGORY));
            notflixs.add(new Notflix(id, title, ratings, sinopsis, date, backdrop, poster, id_item, category));
        }
        return notflixs;
    }
}
