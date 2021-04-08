package com.example.debugexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String shin = "용드로이드";

        Log.e("MainActivity : ", shin);

        int a = 10;

        Log.e("MainActivity : ", String.valueOf(a));


    }
}