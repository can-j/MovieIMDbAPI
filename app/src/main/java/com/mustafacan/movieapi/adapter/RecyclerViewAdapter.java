package com.mustafacan.movieapi.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mustafacan.movieapi.R;
import com.mustafacan.movieapi.model.MovieModel;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RowHolder> {

    private ArrayList<String> movieNamesList;


    public RecyclerViewAdapter(ArrayList<String> movieNames) {
        this.movieNamesList = movieNames;
    }

    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_view, parent, false);
        return new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.RowHolder holder, int position) {
        holder.movieTextName.setText(movieNamesList.get(position));
    }

    @Override
    public int getItemCount() {
        return movieNamesList.size();
    }


    public class RowHolder extends RecyclerView.ViewHolder{
        TextView movieTextName;

        public RowHolder(@NonNull View itemView) {
            super(itemView);
            movieTextName = itemView.findViewById(R.id.movie_name);
        }


    }
}
