# Flix
Flix is an app that allows users to browse movies from the [The Movie Database API](http://docs.themoviedb.apiary.io/#).

#### Features

- [X] Expose details of movie in a separate activity.
- [X] Allow video posts to be played in full-screen using the YouTubePlayerView.
- [X] Trailers for popular movies are played automatically when the movie is selected.
- [X] Add a play icon overlay to popular movies to indicate that the movie can be played.


### App Walkthough GIF

<img src="demo2.gif" width=250> | <img src='screenie.PNG' width='250'> <br>

### Notes

- Movies are considered popular if rating > 3.5/5
- Textview overlay with 'Popular Movie' tag
- Popular movies open fullscreen trailers while less popular show detailed page
- To prevent YouTubePlayer from reinstantiating, put the following in AndroidManifest:
   <activity android:name=".MovieDetailsActivity"
            android:configChanges="screenSize|orientation|keyboardHidden">
   </activity>


## Open-source libraries used
- [Android Async HTTP](https://github.com/codepath/CPAsyncHttpClient) - Simple asynchronous HTTP requests with JSON parsing
- [Glide](https://github.com/bumptech/glide) - Image loading and caching library for Android

