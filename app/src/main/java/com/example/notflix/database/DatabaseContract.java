package com.example.notflix.database;

import android.provider.BaseColumns;

public class DatabaseContract {
    public static String TABLE_NAME = "notflix";
    public static final class NotflixColumns implements BaseColumns {
        public static String TITLE = "title";
        public static String RATINGS = "ratings";
        public static String POSTER = "poster";
        public static String BACKDROP = "backdrop";
        public static String SINOPSIS = "sinopsis";
        public static String DATE = "date";
        public static String ID_ITEM = "ID_ITEM";

    }
}
