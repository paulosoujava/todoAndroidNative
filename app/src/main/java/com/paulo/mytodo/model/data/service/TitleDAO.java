package com.paulo.mytodo.model.data.service;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.paulo.mytodo.model.entity.Title;
import java.util.List;


@Dao
public interface TitleDAO {

    @Query("SELECT * FROM title")
    List<Title> getAll();

    @Query("SELECT * FROM title WHERE title LIKE :title ")
    Title findByName(String title);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Title... titles);

    //@Query("UPDATE title SET title = :title WHERE id = :id")
    //@Query("UPDATE title SET title=:title WHERE id = :id")
    @Update
    int updateTitle(Title title);

    @Delete
    void delete(Title title);
}
