package com.example.moviedb.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviedb.R;
import com.example.moviedb.activities.MovieDetailsActivity;
import com.example.moviedb.databinding.ResultListItemBinding;
import com.example.moviedb.model.Movie;

import static com.example.moviedb.Constants.MOVIE_DATA;

public class MovieAdapter extends PagedListAdapter<Movie, MovieAdapter.ResultViewHolder> {

    private Context context;

    public MovieAdapter(Context context) {
        super(Movie.CALLBACK);
        this.context = context;
    }

    public class ResultViewHolder extends RecyclerView.ViewHolder {

        private ResultListItemBinding resultListItemBinding;

        public ResultViewHolder(@NonNull ResultListItemBinding resultListItemBinding) {
            super(resultListItemBinding.getRoot());
            this.resultListItemBinding = resultListItemBinding;

            resultListItemBinding.getRoot()
                    .setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Movie movie = getItem(position);
                        Log.d("ResultViewHolder", movie.getReleaseDate()+"");
                        Intent intent = new Intent(context, MovieDetailsActivity.class);
                        intent.putExtra(MOVIE_DATA, movie);

                        context.startActivity(intent);
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ResultListItemBinding resultListItemBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.result_list_item, parent, false);

        return new ResultViewHolder(resultListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {

        Movie movie = getItem(position);
//        if (movie.getReleaseDate() !=null &&
//                movie.getReleaseDate().length()>4) {
//            movie.setReleaseDate(movie.getReleaseDate().substring(0, 4));
//        }
        holder.resultListItemBinding.setMovie(movie);
    }
}
