package com.syedsadman16.flixtorapp;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.parceler.Parcels;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class MovieRecyclerAdapter extends
        RecyclerView.Adapter<MovieRecyclerAdapter.ViewHolder> {


    Context mContext;
    ArrayList<Movie> movies;

    public MovieRecyclerAdapter(Context ctx, ArrayList<Movie> m){
        this.movies = m;
        this.mContext = ctx;
    }

    //holds references to elements in recyclerview
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView description;
        public ImageView posterPath;
        public TextView popularTextView;
        LinearLayout linear;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.desc);
            posterPath = (ImageView) itemView.findViewById(R.id.posterPath);
            linear = itemView.findViewById(R.id.row);
            popularTextView = (TextView) itemView.findViewById(R.id.popularText);
        }

        public void populateView(final Movie movie) {
            title.setText(movie.getTitle());
            description.setText(movie.getDescription());
            Glide.with(mContext).load(movie.getPosterPath())
                    .apply(new RequestOptions().placeholder(R.drawable.placeholder)
                            .error(R.drawable.imagenotfound)).into(posterPath);

            //Displaying fullscreen indicator
            if((Double.parseDouble(movie.getRating()) / 2) > 3.5){
                popularTextView.setVisibility(View.VISIBLE);
            }

            linear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    //Check if the position is valid
                    if (position != RecyclerView.NO_POSITION){
                        Movie movie = movies.get(position);
                        Intent intent = new Intent(mContext, MovieDetailsActivity.class);
                        //passing entire movie object. Serialize  movie using parceler
                        intent.putExtra("movie", Parcels.wrap(movie));
                        Log.i("TAG", Integer.toString(position));
                        mContext.startActivity(intent);
                    }
                }
            });
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
