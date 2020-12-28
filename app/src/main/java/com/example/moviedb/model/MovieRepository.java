package com.example.moviedb.model;

import androidx.lifecycle.MutableLiveData;

import com.example.moviedb.service.MovieApiService;
import com.example.moviedb.service.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.moviedb.Constants.API_KEY;

public class MovieRepository {

//    private ArrayList<Movie> movies = new ArrayList<>();
//    private MutableLiveData<List<Movie>> mutableLiveData =
//            new MutableLiveData<>();
//
//    public MutableLiveData<List<Movie>> getMutableLiveData() {
//
//        MovieApiService movieApiService = RetrofitInstance.getService();
//
//        Call<MovieApiResponse> call = movieApiService.getPopularMovies(API_KEY);
//
//        call.enqueue(new Callback<MovieApiResponse>() {
//            @Override
//            public void onResponse(Call<MovieApiResponse> call, Response<MovieApiResponse> response) {
//
//                MovieApiResponse movieApiResponse = response.body();
//                if (movieApiResponse != null &&
//                        movieApiResponse.getMovies() != null) {
//
//                    movies = (ArrayList<Movie>) movieApiResponse.getMovies();
//                    mutableLiveData.setValue(movies);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MovieApiResponse> call, Throwable t) {
//
//            }
//        });
//
//        return mutableLiveData;
//    }
}
