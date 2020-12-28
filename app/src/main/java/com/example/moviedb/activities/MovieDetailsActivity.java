package com.example.moviedb.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.example.moviedb.R;
import com.example.moviedb.databinding.ActivityMovieDetailsBinding;
import com.example.moviedb.model.Movie;

import static com.example.moviedb.Constants.MOVIE_DATA;

public class MovieDetailsActivity extends AppCompatActivity {

    private ActivityMovieDetailsBinding activityMovieDetailsBinding;

    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        activityMovieDetailsBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_movie_details);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(MOVIE_DATA)) {
          movie = intent.getParcelableExtra(MOVIE_DATA);
          movie.setReleaseDate("Release date: "+ movie.getReleaseDate());
          activityMovieDetailsBinding.setMovie(movie);
        }
    }
}