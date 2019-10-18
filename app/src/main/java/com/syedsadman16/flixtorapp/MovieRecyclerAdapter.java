package com.syedsadman16.flixtorapp;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MovieRecyclerAdapter extends
        RecyclerView.Adapter<MovieRecyclerAdapter.ViewHolder> {

    //To know where to inflate the views
    Context mContext;
    //Need the actual data
    ArrayList<Movie> movies;

    public MovieRecyclerAdapter(Context ctx, ArrayList<Movie> m){
        this.movies = m;
        this.mContext = ctx;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView description;
        public ImageView posterPath;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.desc);
            posterPath = (ImageView) itemView.findViewById(R.id.posterPath);
        }

        public void populateView(Movie movie) {
            title.setText(movie.getTitle());
            description.setText(movie.getDescription());
            Glide.with(mContext).load(movie.getPosterPath()).into(posterPath);
        }


    }

    // Inflating a layout file and returning it to view holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflate from XML file
        View movieView = LayoutInflater.from(mContext).inflate(R.layout.recycler_layout, parent, false);
        return new ViewHolder(movieView);
    }



    // Get data from position and put into view holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.populateView(movie);

    }


    @Override
    public int getItemCount() {
        return movies.size();
    }


}
