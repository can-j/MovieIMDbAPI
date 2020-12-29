package com.mustafacan.movieapi.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

import com.mustafacan.movieapi.R;
import com.mustafacan.movieapi.adapter.RecyclerViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MovieListActivity extends AppCompatActivity {

    ArrayList<String> movieNames;
    public String movieName;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        recyclerView = findViewById(R.id.recyclerView);
        movieNames = new ArrayList<String>();

        Intent intent = getIntent();
        movieName = intent.getStringExtra("movieName");
        System.out.println(movieName);

        loadData();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewAdapter = new RecyclerViewAdapter(movieNames);
        System.out.println("item sayisi " + recyclerViewAdapter.getItemCount());
        recyclerView.setAdapter(recyclerViewAdapter);


        // item touch listener
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                return true;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

    }

    private void loadData(){

        OkHttpClient client = new OkHttpClient();

        String url = "https://imdb8.p.rapidapi.com/title/auto-complete?q="+ movieName;

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("x-rapidapi-key","eecdab32ddmsh6ac469aac3b3fb5p1c2987jsneabe8d85bb30")
                .addHeader("x-rapidapi-host", "imdb8.p.rapidapi.com")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){

                    //List<MovieModel> movieModelList = response.body();
                    String myResponse = response.body().string();

                    System.out.println(myResponse);

                    try {
                        JSONObject jsonObject = new JSONObject(myResponse);
                        JSONArray jsonArray = jsonObject.getJSONArray("d");
                        System.out.println(jsonArray);

                        for (int i = 0; i < jsonArray.length(); i++){
                            JSONObject object = jsonArray.getJSONObject(i);
                            System.out.println(object.get("l"));
                            movieNames.add(object.get("l").toString());

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }

}