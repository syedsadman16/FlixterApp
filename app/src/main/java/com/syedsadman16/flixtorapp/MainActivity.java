package com.syedsadman16.flixtorapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.RequestParams;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.codepath.asynchttpclient.callback.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.Headers;

public class MainActivity extends AppCompatActivity {
    ArrayList<Movie> movies = new ArrayList<>();
    public static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Movies");


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvMovie);
        final MovieRecyclerAdapter adapter = new MovieRecyclerAdapter(this, movies);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        AsyncHttpClient client = new AsyncHttpClient();
        //Since using JSON, use HsonHttpResonseHandler
        client.get("https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed", new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                JSONObject jsonObject = json.jsonObject;
                try {
                    JSONArray jsonArray = jsonObject.getJSONArray("results");

                    for(int i=0; i<jsonArray.length(); i++) {
                        String title = jsonArray.getJSONObject(i).getString("title");
                        String desc = jsonArray.getJSONObject(i).getString("overview");
                        String posterPath = jsonArray.getJSONObject(i).getString("poster_path");
                        String rating = jsonArray.getJSONObject(i).getString("vote_average");

                        Movie dwnldMovie = new Movie(title, desc, posterPath);
                        dwnldMovie.setRating(rating);

                        movies.add(dwnldMovie);
                        Log.i(TAG, "Success");
                        adapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    Log.i(TAG, "Async Failure");
                    e.printStackTrace();
                }


            }

            @Override
                    public void onFailure(int statusCode, Headers headers, String errorResponse, Throwable t) {
                        Log.i(TAG, "Async Failure");
                    }
                }
        );




    }
}
