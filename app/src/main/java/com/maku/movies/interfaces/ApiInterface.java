package com.maku.movies.interfaces;

import com.maku.movies.models.MovieResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ApiInterface {

    @GET("api/post/assessment.php/")
    Call<ArrayList<MovieResponse>> getMovieDetails();

}
