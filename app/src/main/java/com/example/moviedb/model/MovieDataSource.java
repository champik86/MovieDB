package com.example.moviedb.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.moviedb.service.MovieApiService;
import com.example.moviedb.service.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.moviedb.Constants.API_KEY;

public class MovieDataSource extends PageKeyedDataSource <Long, Movie> {

    private MovieApiService movieApiService;
    private Application application;

    public MovieDataSource(MovieApiService movieApiService, Application application) {
        this.movieApiService = movieApiService;
        this.application = application;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull LoadInitialCallback<Long, Movie> callback) {

        movieApiService = RetrofitInstance.getService();
        Call<MovieApiResponse> call = movieApiService.getPopularMoviesWithPaging(
                API_KEY,
                1
        );
        call.enqueue(new Callback<MovieApiResponse>() {
            @Override
            public void onResponse(Call<MovieApiResponse> call, Response<MovieApiResponse> response) {

                MovieApiResponse movieApiResponse = response.body();
                ArrayList<Movie> movies = new ArrayList<>();
                if (movieApiResponse != null &&
                        movieApiResponse.getMovies() != null) {
                    movies = (ArrayList<Movie>) movieApiResponse.getMovies();
                    callback.onResult(movies, null, (long) 2);
                }
            }

            @Override
            public void onFailure(Call<MovieApiResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Movie> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params,
                          @NonNull final LoadCallback<Long, Movie> callback) {

        movieApiService = RetrofitInstance.getService();
        Call<MovieApiResponse> call = movieApiService.getPopularMoviesWithPaging(
                API_KEY,
                params.key
        );
        call.enqueue(new Callback<MovieApiResponse>() {
            @Override
            public void onResponse(Call<MovieApiResponse> call, Response<MovieApiResponse> response) {

                MovieApiResponse movieApiResponse = response.body();
                ArrayList<Movie> movies = new ArrayList<>();

                if (movieApiResponse != null &&
                        movieApiResponse.getMovies() != null) {
                    movies = (ArrayList<Movie>) movieApiResponse.getMovies();
                    callback.onResult(movies, params.key + 1);
                }
            }

            @Override
            public void onFailure(Call<MovieApiResponse> call, Throwable t) {

            }
        });
    }
}
