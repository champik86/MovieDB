package com.example.moviedb.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.res.Configuration;
import android.os.Bundle;

import com.example.moviedb.R;
import com.example.moviedb.adapter.MovieAdapter;
import com.example.moviedb.databinding.ActivityMainBinding;
import com.example.moviedb.model.Movie;
import com.example.moviedb.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;

    private PagedList<Movie> movies;
    private RecyclerView recyclerView;

    private MovieAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    private MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityMainBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_main);

        mainActivityViewModel = new ViewModelProvider
                .AndroidViewModelFactory(getApplication())
                .create(MainActivityViewModel.class);

        getPopularMovies();

        swipeRefreshLayout = activityMainBinding.swiperefresh;
        swipeRefreshLayout.setColorSchemeResources(R.color.design_default_color_primary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPopularMovies();
            }
        });
    }

    private void getPopularMovies() {
        mainActivityViewModel.getPagedListLiveData().observe(this,
                new Observer<PagedList<Movie>>() {
                    @Override
                    public void onChanged(PagedList<Movie> movies) {
                        MainActivity.this.movies = movies;
                        fillRecyclerView();
                    }
                });
    }

    private void fillRecyclerView() {

        recyclerView = activityMainBinding.recyclerView;
        adapter = new MovieAdapter(this);
        adapter.submitList(movies);

        if (getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }
}