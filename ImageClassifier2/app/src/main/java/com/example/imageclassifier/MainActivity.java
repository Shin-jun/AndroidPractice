package com.example.imageclassifier;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private static final String CAMERA_PERMMISSION = Manifest.permission.CAMERA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}