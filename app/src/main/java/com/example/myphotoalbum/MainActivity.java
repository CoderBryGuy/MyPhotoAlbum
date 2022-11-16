package com.example.myphotoalbum;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.example.myphotoalbum.database.MyImages;
import com.example.myphotoalbum.viewmodel.MyImagesViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MyImagesViewModel mMyImagesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMyImagesViewModel = new ViewModelProvider
                .AndroidViewModelFactory(getApplication())
                .create(MyImagesViewModel.class);

        mMyImagesViewModel.getAllImages().observe(MainActivity.this, new Observer<List<MyImages>>() {
            @Override
            public void onChanged(List<MyImages> myImages) {

            }
        });
    }
}