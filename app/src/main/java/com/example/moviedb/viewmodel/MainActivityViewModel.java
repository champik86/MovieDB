package com.example.moviedb.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.moviedb.model.Movie;
import com.example.moviedb.model.MovieDataSource;
import com.example.moviedb.model.MovieDataSourceFactory;
import com.example.moviedb.model.MovieRepository;
import com.example.moviedb.service.MovieApiService;
import com.example.moviedb.service.RetrofitInstance;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivityViewModel extends AndroidViewModel {

    private MovieRepository movieRepository;
    private LiveData<MovieDataSource> movieDataSourceLiveData;
    private Executor executor;
    private LiveData<PagedList<Movie>> pagedListLiveData;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new MovieRepository();

        MovieApiService movieApiService = RetrofitInstance.getService();
        MovieDataSourceFactory movieDataSourceFactory =
                new MovieDataSourceFactory(application, movieApiService);
        movieDataSourceLiveData = movieDataSourceFactory.getMutableLiveData();

        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(20)
                .setPrefetchDistance(3)
                .build();

        executor = Executors.newCachedThreadPool();

        pagedListLiveData = new LivePagedListBuilder<Long, Movie>(
                movieDataSourceFactory, config
        ).setFetchExecutor(executor)
                .build();
    }

//    public LiveData<List<Movie>> getAllMovieData() {
//        return movieRepository.getMutableLiveData();
//    }

    public LiveData<PagedList<Movie>> getPagedListLiveData() {
        return pagedListLiveData;
    }
}
