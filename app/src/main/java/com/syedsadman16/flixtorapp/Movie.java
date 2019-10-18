package com.syedsadman16.flixtorapp;

public class Movie {
    private String title;
    private String description;
    private String posterPath;

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

}
