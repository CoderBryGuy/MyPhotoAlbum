package com.example.myphotoalbum;

import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myphotoalbum.database.MyImages;
import com.example.myphotoalbum.viewmodel.MyImagesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MyImagesViewModel mMyImagesViewModel;

    private FloatingActionButton mFab;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFab = findViewById(R.id.fab_main_vw);
        mRecyclerView = findViewById(R.id.recyclerView_main_vw);

        mMyImagesViewModel = new ViewModelProvider
                .AndroidViewModelFactory(getApplication())
                .create(MyImagesViewModel.class);

        mMyImagesViewModel.getAllImages().observe(MainActivity.this, new Observer<List<MyImages>>() {
            @Override
            public void onChanged(List<MyImages> myImages) {

            }
        });

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}