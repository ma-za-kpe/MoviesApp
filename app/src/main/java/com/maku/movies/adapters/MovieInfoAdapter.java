package com.maku.movies.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.maku.movies.R;
import com.maku.movies.models.MovieResponse;
import com.maku.movies.ui.MainActivity;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MovieInfoAdapter extends RecyclerView.Adapter<MovieInfoAdapter.ViewHolder> {

    Context mContext;
    ArrayList<MovieResponse> mMovieResponseArrayList;
    Inflater mInflater;

    public MovieInfoAdapter(Context context, ArrayList<MovieResponse> movieResponseArrayList) {
        mContext = context;
        mMovieResponseArrayList = movieResponseArrayList;
    }

    @NonNull
    @Override
    public MovieInfoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_item, parent, false);
        MovieInfoAdapter.ViewHolder viewHolder = new MovieInfoAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieInfoAdapter.ViewHolder holder, int position) {

        holder.name.setText(mMovieResponseArrayList.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return mMovieResponseArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.movieName);
        }
    }
}
