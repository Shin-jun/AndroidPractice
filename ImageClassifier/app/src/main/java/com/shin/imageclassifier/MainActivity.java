package com.shin.imageclassifier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button galleyBtn = findViewById(R.id.galleryBtn);
        galleyBtn.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, gallery.class);
            startActivity(i);
        });

        Button cameraBtn = findViewById(R.id.cameraBtn);
        cameraBtn.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, Camera.class);
            startActivity(i);
        });
    }
}