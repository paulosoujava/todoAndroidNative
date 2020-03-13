package com.paulo.mytodo.model.entity;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.paulo.mytodo.utils.Prefs;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Title implements Parcelable {

    public static final String TITLE_KEY = "TITLE_KEY";
    @NonNull
    @PrimaryKey
    private String id;
    @ColumnInfo(name = "title")
    private String title;



    public Title(String title) {
        this.title = title;
    }

    protected Title(Parcel in) {
        id = in.readString();
        title = in.readString();
    }

    public static final Creator<Title> CREATOR = new Creator<Title>() {
        @Override
        public Title createFromParcel(Parcel in) {
            return new Title(in);
        }

        @Override
        public Title[] newArray(int size) {
            return new Title[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Title{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Title title1 = (Title) o;
        return id.equals(title1.id) &&
                Objects.equals(title, title1.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}
