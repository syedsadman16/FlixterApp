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

    public void setRating(String r){
        rating = r;
       // int number = Integer.parseInt(r);
       // rating = number / 2;
    }

    public String getRating(){
        return rating;
    }

    public String getDescription(){
        return description;
    }

    public String getPosterPath(){
        return "https://image.tmdb.org/t/p/w342/" + posterPath;
    }

    public void setBackDrop(String bd) {
        backDrop = bd;
    }

    public String getBackDrop() {
        return "https://image.tmdb.org/t/p/w342/" + backDrop;
    }

    public void setVideoId(String videoid) {
        id = videoid;
    }

    public String getVideoID(){
        return id;
    }

}
