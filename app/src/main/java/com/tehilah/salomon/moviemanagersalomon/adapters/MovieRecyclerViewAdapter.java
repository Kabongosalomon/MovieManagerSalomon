package com.tehilah.salomon.moviemanagersalomon.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tehilah.salomon.moviemanagersalomon.R;
import com.tehilah.salomon.moviemanagersalomon.activities.MovieDetailActivity;
import com.tehilah.salomon.moviemanagersalomon.models.Movie;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by salomon on 3/27/18.
 */

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder> {

    List<Movie> movies;
    Context context;

    public MovieRecyclerViewAdapter(Context context,List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    private Context getContext(){
        return context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Movie movie =movies.get(position);

        holder.tvTitle.setText(movie.getTitle());
        holder.tvOverview.setText(movie.getOverview());

        Picasso.with(getContext())
                .load(movie.getPosterPath())
                .into(holder.ivMovieImage);

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder implements  View.OnClickListener{


        @BindView(R.id.ivMovieImage)
        ImageView ivMovieImage;
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.tvOverview)
        TextView tvOverview;
        @BindView(R.id.cvMovie)
        CardView cvMovie;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Movie movie = movies.get(getAdapterPosition());
            Intent intent =new Intent(getContext(), MovieDetailActivity.class);
            intent.putExtra("Movie", movie);
            getContext().startActivity(intent);
        }
    }
}
