package com.example.myphotoalbum;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

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

            }
        });

        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}