package com.paulo.mytodo.model.data.service;

import android.content.Context;
import android.util.Log;

import com.paulo.mytodo.model.entity.ItemTitle;
import com.paulo.mytodo.model.entity.Title;

import java.util.List;

public class Service {

    static AppDatabase db;

    public Service(Context context) {
        db = AppDatabase.getInstance(context);
    }
    public  List<Title> getListTitle(){

        return  db.titleDAO().getAll();
    }
    public  List<ItemTitle> getItemsTitle(String uidTitle){
       return  db.itemTitleDAO().loadAllByIds(uidTitle);
    }
    public  Title existTtile(String title){
        return  db.titleDAO().findByName(title.toLowerCase().trim());
    }
    public  void createTitle( Title title ){
         db.titleDAO().insertAll(title);
    }
    public  void createItemTitle( ItemTitle itemTitle ){
        db.itemTitleDAO().insertAll(itemTitle);
    }
    public  void updateTitle( Title title ){
         db.titleDAO().updateTitle(title);
    }
    public  void updateItemTitle( ItemTitle item ){int idx = db.itemTitleDAO().updateTitle(item);}
    public  void deleteTitle(Title title){
        db.titleDAO().delete(title);
        db.itemTitleDAO().deleteByID(title.getId());
    }
    public  void deleteItem( ItemTitle itemTitle ){
        db.itemTitleDAO().delete(itemTitle);
    }

    public int getCount(String uid) {
        return  db.itemTitleDAO().count(uid);
    }
}
