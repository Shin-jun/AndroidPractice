package com.example.debugexmaple;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String android = "안드로이드";

        Log.e("MainActivity : ", android);

        int a = 10;

        Log.e("MainActivity", String.valueOf(a));
    }
}