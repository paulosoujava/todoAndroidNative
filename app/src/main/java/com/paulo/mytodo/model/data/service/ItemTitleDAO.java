package com.paulo.mytodo.model.data.service;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.paulo.mytodo.model.entity.ItemTitle;


import java.util.List;

@Dao
public interface ItemTitleDAO {
    @Query("SELECT * FROM itemtitle WHERE uid_title IN (:titleId)")
    List<ItemTitle> loadAllByIds(String titleId);

    //@Query("UPDATE itemtitle SET hour=:hour, date=:date, others=:others WHERE uid IN (:id)")
    //String hour, String date, String others, String id
    @Update
    int updateTitle(ItemTitle itemTitle);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(ItemTitle... itemTitles);

    @Delete
    void delete(ItemTitle itemTitle);

    @Query("DELETE  FROM itemtitle WHERE uid_title IN (:uid)")
    void deleteByID(String uid);


    @Query("SELECT COUNT(uid_title) FROM itemtitle WHERE uid_title IN (:titleId)")
    int count(String titleId);
}
