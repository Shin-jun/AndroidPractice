package com.shin.imageclassifier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.shin.imageclassifier.tflite.ClassifierWithModel;

import java.io.IOException;

public class gallery extends AppCompatActivity {
    public static final int GALLERY_IMAGE_REQUEST_CODE = 1;

    private ClassifierWithModel cls;
    private ImageView imageView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        Button selectBtn = findViewById(R.id.selectBtn);
        selectBtn.setOnClickListener(v -> getImageFromGallery());

        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);

        cls = new ClassifierWithModel(this);
        try {
            cls.init();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }
    private void getImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT).setType("image/*");
        startActivityForResult(intent, GALLERY_IMAGE_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode)
    }
}