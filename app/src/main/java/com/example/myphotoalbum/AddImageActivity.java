package com.example.myphotoalbum;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class AddImageActivity extends AppCompatActivity {

    private EditText mTitle, mDesc;
   private Button mSaveBtn;

   private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Add Image");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_add_imagine);

        mTitle = findViewById(R.id.title_add_img_act);
        mDesc = findViewById(R.id.description_add_img_act);
        mSaveBtn = findViewById(R.id.save_btn_add_img_act);
        mImageView = findViewById(R.id.image_vw_add_img_act);

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if(ContextCompat.checkSelfPermission(AddImageActivity.this,
                            Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){

                        ActivityCompat.requestPermissions(AddImageActivity.this,
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                1);

                    }else{
                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    }
            }
        });

        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}