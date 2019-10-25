package com.syedsadman16.flixtorapp;

import org.parceler.Parcel;


//Need to annotate to Parcel
@Parcel
public class Movie {
    private String title;
    private String description;
    private String posterPath;
    private String rating;
    private String backDrop;
    private String id;

    //Parcel needs empty movie adapter
    public Movie(){}

    public Movie(String t, String d, String p) {
       description = d;
       posterPath = p;
       title = t;
    }

    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
    }

    public String getPosterPath(){
        return "https://image.tmdb.org/t/p/w342/" + posterPath;
    }

    public String getRating(){
        return rating;
    }

    public String getBackDrop() {
        return "https://image.tmdb.org/t/p/w342/" + backDrop;
    }

    public String getVideoID(){
        return id;
    }

    public void setRating(String r){ rating = r; }

    public void setBackDrop(String bd) {
        backDrop = bd;
    }

    public void setVideoId(String videoid) {
        id = videoid;
    }


}
