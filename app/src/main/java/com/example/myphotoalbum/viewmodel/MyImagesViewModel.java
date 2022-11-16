package com.example.myphotoalbum.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.myphotoalbum.database.MyImages;
import com.example.myphotoalbum.database.MyImagesDAO;
import com.example.myphotoalbum.repository.MyImagesRepository;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MyImagesViewModel extends AndroidViewModel {

    private MyImagesRepository mRepository;
    private LiveData<List<MyImages>> mImagesList;

    public MyImagesViewModel(@NonNull @NotNull Application application) {
        super(application);

        mRepository = new MyImagesRepository(application);
        mImagesList = mRepository.getAllImages();
    }

    public void insert(MyImages myImages){
        mRepository.insert(myImages);
    }
    public void delete(MyImages myImages){
        mRepository.delete(myImages);
    }
    public void update(MyImages myImages){
        mRepository.update(myImages);
    }

    public LiveData<List<MyImages>> getAllImages(){
        return mImagesList;
    }

}
