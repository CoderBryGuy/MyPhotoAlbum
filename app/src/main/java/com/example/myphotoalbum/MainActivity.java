package com.example.myphotoalbum;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myphotoalbum.database.MyImages;
import com.example.myphotoalbum.viewmodel.MyImagesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.example.myphotoalbum.MainActivity.TagContracts.*;

public class MainActivity extends AppCompatActivity {




    class TagContracts{
        final static String TITLE = "title", DESCRIPTION = "desc", IMAGE = "image";
        final static String TITLE_UPDATE = "title_update", DESCRIPTION_UPDATE = "desc_update",
                IMAGE_UPDATE = "image_update", ID_UPDATE = "id_update";

    }

    private MyImagesViewModel mMyImages_ViewModel;

    private FloatingActionButton mFab;
    private RecyclerView mRecyclerView;

    private ActivityResultLauncher<Intent> mIntentActivity_ResultLauncher_For_Add_Image;
    private ActivityResultLauncher<Intent> mIntentActivity_ResultLauncher_For_Update_Image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //register activity
        registerActivityForAddImage();
        registerActivityForUpdateImage();


        mFab = findViewById(R.id.fab_main_vw);
        mRecyclerView = findViewById(R.id.recyclerView_main_vw);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        MyImagesAdapter mAdapter = new MyImagesAdapter();
        mRecyclerView.setAdapter(mAdapter);

        mMyImages_ViewModel = new ViewModelProvider
                .AndroidViewModelFactory(getApplication())
                .create(MyImagesViewModel.class);

        mMyImages_ViewModel.getAllImages().observe(MainActivity.this, new Observer<List<MyImages>>() {
            @Override
            public void onChanged(List<MyImages> myImages) {
                mAdapter.setImagesList(myImages);
            }
        });

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, AddImageActivity.class);
                //activityResultLauncher
                mIntentActivity_ResultLauncher_For_Add_Image.launch(intent);

            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull @NotNull RecyclerView recyclerView, @NonNull @NotNull RecyclerView.ViewHolder viewHolder, @NonNull @NotNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull @NotNull RecyclerView.ViewHolder viewHolder, int direction) {
                mMyImages_ViewModel.delete(mAdapter.getPosition(viewHolder.getAdapterPosition()));

            }
        }).attachToRecyclerView(mRecyclerView);

        mAdapter.setListener(new MyImagesAdapter.OnImageClickListener() {
            @Override
            public void onImageClick(MyImages myImages) {
                Intent intent = new Intent(MainActivity.this, UpdateImageActivity.class);
                intent.putExtra(ID_UPDATE, myImages.getImage_id());
                intent.putExtra(TITLE_UPDATE, myImages.getImage_title());
                intent.putExtra(DESCRIPTION_UPDATE, myImages.getImage_description());
                intent.putExtra(IMAGE_UPDATE, myImages.getImage());
                //activity result launcher
                mIntentActivity_ResultLauncher_For_Update_Image.launch(intent);


            }
        });
    }

    private void registerActivityForUpdateImage() {

        mIntentActivity_ResultLauncher_For_Update_Image = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        int resultCode = result.getResultCode();
                        Intent data = result.getData();
                        if(resultCode == RESULT_OK && data != null){
                            String title = data.getStringExtra(TITLE);
                            String desc = data.getStringExtra(DESCRIPTION);
                            byte[] image = data.getByteArrayExtra(IMAGE);

                            MyImages myImages = new MyImages(title, desc, image);
//                            mMyImages_ViewModel.insert(myImages);
                            mMyImages_ViewModel.update(myImages);
                        }
                    }
                }
        );
    }

    public void registerActivityForAddImage() {
        mIntentActivity_ResultLauncher_For_Add_Image = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        int resultCode = result.getResultCode();
                        Intent data = result.getData();

                        if (resultCode == RESULT_OK && data != null) {
                            String title = data.getStringExtra(TITLE);
                            String desc = data.getStringExtra(DESCRIPTION);
                            byte[] image = data.getByteArrayExtra(IMAGE);

                            MyImages myImages = new MyImages(title, desc, image);
                            mMyImages_ViewModel.insert(myImages);
                        }
                    }
                }
        );
    }
}