package com.example.myphotoalbum;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.os.Build;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AddImageActivity extends AppCompatActivity {

    private EditText mTitle, mDesc;
    private Button mSaveBtn;

    private ImageView mImageView;

    ActivityResultLauncher<Intent> mIntentActivity_ResultLauncher_ForSelectImage;

    private Bitmap selectedImage;
    private Bitmap scaledImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Add Image");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_add_imagine);

        //register activity
        registerActivityForSelectImage();


        mTitle = findViewById(R.id.title_add_img_act);
        mDesc = findViewById(R.id.description_add_img_act);
        mSaveBtn = findViewById(R.id.save_btn_add_img_act);
        mImageView = findViewById(R.id.image_vw_add_img_act);


        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(selectedImage == null){
                    Toast.makeText(AddImageActivity.this, "Please select an image!", Toast.LENGTH_SHORT).show();
                }else {

                    String title = mTitle.getText().toString();
                    String description = mDesc.getText().toString();
                    //get bitmap
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    scaledImage = makeSmall(selectedImage, 300);
                    scaledImage.compress(Bitmap.CompressFormat.PNG, 50, outputStream);
                    byte[] image = outputStream.toByteArray();

                    Intent intent = new Intent();
                    intent.putExtra(MainActivity.TagContracts.TITLE_ADD, title);
                    intent.putExtra(MainActivity.TagContracts.DESCRIPTION_ADD, description);
                    intent.putExtra(MainActivity.TagContracts.IMAGE_ADD, image);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ContextCompat.checkSelfPermission(AddImageActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(AddImageActivity.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            1);

                } else {
                    //permission granted
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    mIntentActivity_ResultLauncher_ForSelectImage.launch(intent);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            mIntentActivity_ResultLauncher_ForSelectImage.launch(intent);
        }
    }

    public void registerActivityForSelectImage() {

        mIntentActivity_ResultLauncher_ForSelectImage
                = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        int resultCode = result.getResultCode();
                        Intent data = result.getData();

                        if (resultCode == RESULT_OK && data != null) {


                            if (Build.VERSION.SDK_INT < 28) {
                                try {
                                    selectedImage = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            } else {
                                ImageDecoder.Source source = ImageDecoder.createSource(getContentResolver(), data.getData());
                                try {
                                    selectedImage = ImageDecoder.decodeBitmap(source);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }

                            mImageView.setImageBitmap(selectedImage);

                        }
                    }

                });
    }

    public Bitmap makeSmall(Bitmap image, int maxSize) {

        int width = selectedImage.getWidth();
        int height = selectedImage.getHeight();

        float ratio = (float) width / (float) height;

        if (ratio > 1) {
            width = maxSize;
            height = (int) (width / ratio);
        } else {
            height = maxSize;
            width = (int)(height * ratio);
        }

        return Bitmap.createScaledBitmap(image, width, height, true);
    }


}