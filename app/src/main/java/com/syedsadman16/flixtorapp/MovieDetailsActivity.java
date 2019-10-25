package com.syedsadman16.flixtorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import java.net.URL;

import jp.wasabeef.glide.transformations.BlurTransformation;
import okhttp3.Headers;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;
import static com.facebook.stetho.inspector.network.ResponseHandlingInputStream.TAG;

public class MovieDetailsActivity extends YouTubeBaseActivity {

    Movie movies;
    ImageView detailImageView;
    TextView movieTitle, movieRating, movieDesc,popularTextView;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);


        movieTitle = (TextView) findViewById(R.id.movieName);
        movieRating = (TextView) findViewById(R.id.movieRating);
        movieDesc = (TextView) findViewById(R.id.movieDesc);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        movies = (Movie) Parcels.unwrap(getIntent().getParcelableExtra("movie"));
        setTitle(movies.getTitle());
        movieTitle.setText(movies.getTitle());
        movieDesc.setText(movies.getDescription());
        movieRating.setText("Rating: " + movies.getRating() + "/10");
        Drawable drawable = ratingBar.getProgressDrawable();
        drawable.setColorFilter(Color.parseColor("#0064A8"), PorterDuff.Mode.SRC_ATOP);
        ratingBar.setRating(Float.parseFloat(movies.getRating()) / 2);

        detailImageView = (ImageView) findViewById(R.id.detailImageView);
        Glide.with(this).load(movies.getBackDrop())
                .apply(new RequestOptions()
                        .placeholder(new ColorDrawable(this.getResources().getColor(R.color.background)))
                        .apply(bitmapTransform(new BlurTransformation(95)))
                        .error(R.drawable.imagenotfound)).into(detailImageView);

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("https://api.themoviedb.org/3/movie/"+movies.getVideoID()+"/videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed",
                new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Headers headers, JSON json) {
                        String key = "";
                        JSONObject jsonObject = json.jsonObject;

                        try {
                            JSONArray jsonArray = jsonObject.getJSONArray("results");
                            key = jsonArray.getJSONObject(0).getString("key");

                            // temporary test video id -- TODO replace with movie trailer video id
                            final String videoId = key;

                            // resolve the player view from the layout
                            final YouTubePlayerView playerView = (YouTubePlayerView) findViewById(R.id.player);

                            // strings.xml
                            playerView.initialize(getString(R.string.youtube_api_key), new YouTubePlayer.OnInitializedListener() {
                                @Override
                                public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                                    YouTubePlayer youTubePlayer, boolean b) {
                                    youTubePlayer.cueVideo(videoId);
                                    if((Double.parseDouble(movies.getRating()) / 2) > 3.5){
                                        youTubePlayer.loadVideo(videoId);
                                        youTubePlayer.setFullscreen(true);
                                    }
                                }

                                @Override
                                public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                                    YouTubeInitializationResult youTubeInitializationResult) {
                                    // log the error
                                    Log.e("MovieTrailerActivity", "Error initializing YouTube player");
                                }
                            });


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {

                    }
                });

    }
}
//Glide Transformation Library https://github.com/wasabeef/glide-transformations