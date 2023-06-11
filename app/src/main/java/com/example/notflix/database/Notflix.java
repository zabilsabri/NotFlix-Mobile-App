package com.example.notflix.database;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Notflix implements Parcelable {
    private int id;
    private String title, ratings, sinopsis, date, backdrop, poster, id_item_str, category;
    private int id_item;

    public Notflix(){

    }
    public Notflix(int id, String title, String ratings, String sinopsis, String date, String backdrop, String poster, String id_item_str, String category) {
        this.id = id;
        this.title = title;
        this.ratings = ratings;
        this.sinopsis = sinopsis;
        this.date = date;
        this.backdrop = backdrop;
        this.poster = poster;
        this.id_item_str = id_item_str;
        this.category = category;
    }

    protected Notflix(Parcel in) {
        id = in.readInt();
        title = in.readString();
        ratings = in.readString();
        sinopsis = in.readString();
        date = in.readString();
        backdrop = in.readString();
        poster = in.readString();
        id_item_str = in.readString();
        category = in.readString();
    }

    public static final Creator<Notflix> CREATOR = new Creator<Notflix>() {
        @Override
        public Notflix createFromParcel(Parcel in) {
            return new Notflix(in);
        }

        @Override
        public Notflix[] newArray(int size) {
            return new Notflix[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public int getId_item() {
        id_item = Integer.parseInt(id_item_str);
        return id_item;
    }

    public void setId_item(String id_item) {
        this.id_item_str = id_item_str;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(sinopsis);
        parcel.writeString(date);
        parcel.writeString(ratings);
        parcel.writeString(poster);
        parcel.writeString(backdrop);
    }
}
