package com.example.myphotoalbum.database;

import androidx.lifecycle.LiveData;
import androidx.room.*;

import java.util.List;

@Dao
public interface MyImagesDAO {

    @Insert
    void insert(MyImages myImages);

    @Delete
    void delete(MyImages myImages);

    @Update
    void update(MyImages myImages);

    @Query("SELECT * FROM my_images ORDER BY image_id ASC")
    LiveData<List<MyImages>> getAllImages();

}
