package com.example.myphotoalbum.database;


import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.*;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import org.jetbrains.annotations.NotNull;

@Database(entities = MyImages.class, version = 1)
public abstract class MyImagesDatabase extends RoomDatabase {

    private static MyImagesDatabase instance;
    public abstract MyImagesDAO mMyImagesDAO();

    public static synchronized MyImagesDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    MyImagesDatabase.class,
                    "my_images_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }

}
