package com.shin.sampleshin;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String[] myDataset = {"1", "2"};
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);



        queue = Volley.newRequestQueue(this);
        getNews();
        // 1. 화면이 로딩 -> 뉴스 정보를 받아온다.
        // 2. 정보 -> 어댑터 넘겨준다.
        // 3. 어댑터 -> 세팅
    }

    public void getNews() {

        String url ="https://newsapi.org/v2/top-headlines?country=kr&apiKey=b5233fb6929d46fcac4b5191d439e5b9";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObj = new JSONObject(response);

                            JSONArray arrayArticles = jsonObj.getJSONArray("articles");

                            // response ->> NewsData Class 분류
                            List<NewsData> news = new ArrayList<>();
                            for(int i=0,j=arrayArticles.length(); i<j; i++) {
                                JSONObject obj = arrayArticles.getJSONObject(i);

                                Log.d("NEWS", obj.toString());

                                NewsData newsData = new NewsData();
                                newsData.setTitle(obj.getString("title"));
                                newsData.setUrlToImage(obj.getString(obj.getString("urlToImage")));
                                newsData.setContent(obj.getString("content"));
                                news.add(newsData);
                            }

                            mAdapter = new MyAdapter(news, NewsActivity.this);
                            mRecyclerView.setAdapter(mAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }
}