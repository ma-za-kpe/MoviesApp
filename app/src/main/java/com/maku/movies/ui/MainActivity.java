package com.maku.movies.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.maku.movies.R;
import com.maku.movies.adapters.MovieInfoAdapter;
import com.maku.movies.interfaces.ApiInterface;
import com.maku.movies.models.MovieResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    //widgets
    private RecyclerView mRecyclerView;
    private TextView mTextView;

    //vars
    private LinearLayoutManager mLinearLayoutManager;
    private MovieInfoAdapter mMovieInfoAdapter;
    ArrayList<MovieResponse> mMovieResponseArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.moviewRecyclerView);
        mTextView = findViewById(R.id.textView2);

        mMovieResponseArrayList = new ArrayList<>();

        initRecyclerview();
        getMovies();

    }

    private void getMovies() {

        String url = "https://admin.coverappke.com/";

        Log.d(TAG, "getMovies: url " + url);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(okClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        ApiInterface mApiInterface = retrofit.create(ApiInterface.class);
        Call<ArrayList<MovieResponse>> call = mApiInterface.getMovieDetails();

        call.enqueue(new Callback<ArrayList<MovieResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<MovieResponse>> call, Response<ArrayList<MovieResponse>> response) {

                if (response.isSuccessful())  {
                    Log.d(TAG, "onResponse: " + response.toString());
                    process(response);
                }else{
                    Log.d(TAG, "onResponse: there is no response");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<MovieResponse>> call, Throwable t) {
                Log.e(TAG,t.toString());
            }
        });

    }

    private void process(Response<ArrayList<MovieResponse>> response) {

        mMovieResponseArrayList =  response.body();
        Log.d(TAG, "process: " + mMovieResponseArrayList);
        mMovieInfoAdapter = new MovieInfoAdapter(this, mMovieResponseArrayList);
        mRecyclerView.setAdapter(mMovieInfoAdapter);

    }

    private void initRecyclerview() {
        Log.d(TAG, "initRecyclerview: ");
        mLinearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
    }

    private static OkHttpClient okClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .build();
    }
}
