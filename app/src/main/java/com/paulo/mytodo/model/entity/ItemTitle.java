package com.paulo.mytodo.model.entity;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

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
public class ItemTitle implements Parcelable {

    public static final String ITEM_TITLE = "ITEM_TITLE";

    @NonNull
    @PrimaryKey
    private String uid;
    @ColumnInfo(name = "uid_title")
    private String uidTitle;
    @ColumnInfo(name = "date")
    private String date;
    @ColumnInfo(name = "hour")
    private String hour;
    @ColumnInfo(name = "others")
    private String others;
    @ColumnInfo(name = "status")
    private String status;


    public ItemTitle() {}

    protected ItemTitle(Parcel in) {
        uidTitle = in.readString();
        uid = in.readString();
        date = in.readString();
        hour = in.readString();
        others = in.readString();
        status = in.readString();
    }

    public static final Creator<ItemTitle> CREATOR = new Creator<ItemTitle>() {
        @Override
        public ItemTitle createFromParcel(Parcel in) {
            return new ItemTitle(in);
        }

        @Override
        public ItemTitle[] newArray(int size) {
            return new ItemTitle[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uidTitle);
        dest.writeString(uid);
        dest.writeString(date);
        dest.writeString(hour);
        dest.writeString(others);
        dest.writeString(status);
    }


    public String getUidTitle() {
        return uidTitle;
    }

    public void setUidTitle(String uidTitle) {
        this.uidTitle = uidTitle;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ItemTitle{" +
                "uid='" + uid + '\'' +
                ", uidTitle='" + uidTitle + '\'' +
                ", date='" + date + '\'' +
                ", hour='" + hour + '\'' +
                ", others='" + others + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemTitle itemTitle = (ItemTitle) o;
        return uid.equals(itemTitle.uid) &&
                uidTitle.equals(itemTitle.uidTitle) &&
                date.equals(itemTitle.date) &&
                hour.equals(itemTitle.hour) &&
                others.equals(itemTitle.others);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, uidTitle, date, hour, others);
    }
}
