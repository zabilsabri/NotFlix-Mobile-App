package com.example.notflix.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;

public class NotflixHelper {
    private static final String DATABASE_TABLE = DatabaseContract.TABLE_NAME;
    private static DatabaseHelper databaseHelper;
    private static SQLiteDatabase database;
    private static volatile NotflixHelper INSTANCE;
    public NotflixHelper(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }
    public static NotflixHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new NotflixHelper(context);
                }
            }
        }
        return INSTANCE;
    }
    public void open() throws SQLException {
        database = databaseHelper.getWritableDatabase();
    }
    public void close() {
        databaseHelper.close();
        if (database.isOpen()) {
            database.close();
        }
    }
    public Cursor queryAll() {
        return database.query(
                DATABASE_TABLE,
                null,
                null,
                null,
                null,
                null,
                DatabaseContract.NotflixColumns._ID + " ASC"
        );
    }
    public Cursor queryByNote(String id) {
        String query = String.format("SELECT * FROM %s WHERE %s == ?", DATABASE_TABLE, DatabaseContract.NotflixColumns.ID_ITEM);
        return database.rawQuery(query, new String[]{id});
    }
    public long insert(ContentValues values) {
        return database.insert(DATABASE_TABLE, null, values);
    }
    public int update(String id, ContentValues values) {
        return database.update(DATABASE_TABLE, values, DatabaseContract.NotflixColumns._ID
                + " = ?", new String[]{id});
    }
    public static int deleteById(String id) {
        return database.delete(DATABASE_TABLE, DatabaseContract.NotflixColumns.ID_ITEM + " = "
                + id, null);
    }
}
