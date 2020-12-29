package com.mustafacan.movieapi.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mustafacan.movieapi.R;

public class MainActivity extends AppCompatActivity {

    EditText searchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchText = findViewById(R.id.searchEditText);

    }

    public void searchButtonClick(View view){
        Intent intent = new Intent(getApplicationContext(), MovieListActivity.class);

        String movieName = searchText.getText().toString();

        if (movieName == null || movieName.isEmpty() || movieName.trim().equals("null") || movieName.trim().isEmpty()){
            Toast.makeText(getApplicationContext(), "Enter movie name", Toast.LENGTH_LONG).show();
        }else {
            intent.putExtra("movieName", movieName.toString());
            startActivity(intent);
        }
    }

}