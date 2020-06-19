package com.example.compositionworkmanytomany;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface HobbiesDao {
    @Insert
    void insert(Hobby hobby);

    @Query("select *from hobbies")
    List<Hobby> getAllHobies();

    @Query("select _id from hobbies where name=:name")
    int getHobbiesIdByName(String name);
}

