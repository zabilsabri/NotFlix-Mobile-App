package com.example.notflix.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "Notflix.db";
    private static final int DATABASE_VERSION = 1;
    private static final String SQL_CREATE_TABLE_NOTFLIX =
            String.format(
                    "CREATE TABLE %s"
                            + " (%s INTEGER PRIMARY KEY AUTOINCREMENT,"
                            + " %s TEXT NOT NULL,"
                            + " %s TEXT NOT NULL,"
                            + " %s TEXT NOT NULL,"
                            + " %s TEXT NOT NULL,"
                            + " %s TEXT NOT NULL,"
                            + " %s TEXT NOT NULL,"
                            + " %s TEXT NOT NULL,"
                            + " %s TEXT NOT NULL)",
                    DatabaseContract.TABLE_NAME,
                    DatabaseContract.NotflixColumns._ID,
                    DatabaseContract.NotflixColumns.TITLE,
                    DatabaseContract.NotflixColumns.RATINGS,
                    DatabaseContract.NotflixColumns.DATE,
                    DatabaseContract.NotflixColumns.POSTER,
                    DatabaseContract.NotflixColumns.BACKDROP,
                    DatabaseContract.NotflixColumns.SINOPSIS,
                    DatabaseContract.NotflixColumns.ID_ITEM,
                    DatabaseContract.NotflixColumns.CATEGORY
            );

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_NOTFLIX);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
