package com.example.videoview;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import java.net.URI;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private VideoView videoView;    // 비디오를 실행 도와주는 뷰
    private MediaController mediaController;    // 재생이나 정지와 같은 미디어 제어 버튼부를 담당
    private String videoURL = "https://www.radiantmediaplayer.com/media/bbb-360p.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) { // 앱이 첫 실행됬을때 이곳을 실행
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = findViewById(R.id.videoView);
        mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        Uri uri = Uri.parse(videoURL);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri); // 비디오 뷰에 주소를 설정
        videoView.start();  // 비디오 실행

    }
}