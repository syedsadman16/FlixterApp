package com.syedsadman16.flixtorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import org.parceler.Parcels;

import java.net.URL;

public class MovieDetailsActivity extends AppCompatActivity {

    Movie movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        movies = (Movie) Parcels.unwrap(getIntent().getParcelableExtra("movie"));
        setTitle(movies.getTitle());
/*
        ImageView imageView = findViewById(R.id.detailImageView);
        try {
            URL url = new URL(movies.getPosterPath());
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            imageView.setImageBitmap(bmp);
        } catch(Exception e) {
            e.printStackTrace();
        }
*/



    }
}
