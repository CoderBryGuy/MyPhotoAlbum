package com.example.myphotoalbum;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class UpdateImageActivity extends AppCompatActivity {


    private EditText mTitle, mDesc;
    private Button mSaveBtn;

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setTitle("Update Image");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_update_image);

        mTitle = findViewById(R.id.title_update_img_act);
        mDesc = findViewById(R.id.description_update_img_act);
        mSaveBtn = findViewById(R.id.update_btn_update_img_act);
        mImageView = findViewById(R.id.image_vw_update_img_act);

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}