package com.paulo.mytodo.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.paulo.mytodo.R;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class Utils {

    public static void hideFloat(RecyclerView recyclerView, FloatingActionButton mFab) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    mFab.hide();
                }
                if (dy < 0)
                    mFab.show();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
    }

    public static void toast(String str, Context context) {
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }

    public static String MyUID(){
        return UUID.randomUUID().toString();
    }

    public static AlertDialog.Builder getBuilder(Context context) {
        return new AlertDialog.Builder(context );
    }
    public  static  LayoutInflater getLayout(Context context){
        return  (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
    }

    public static String date(){
        Date dt = new Date();
        DateFormat df2 = DateFormat.getDateInstance(DateFormat.SHORT, new Locale("pt", "BR"));
        return df2.format(dt);
    }
    public  static String hour(){
        Date dt = new Date();
        return (dt.getHours() +":" + dt.getMinutes());
    }
}
