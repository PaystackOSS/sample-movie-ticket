package com.paystack.samplemovieticket.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.paystack.samplemovieticket.BuildConfig;
import com.paystack.samplemovieticket.R;
import com.paystack.samplemovieticket.adapter.NowPlayingAdapter;
import com.paystack.samplemovieticket.adapter.UpcomingAdapter;
import com.paystack.samplemovieticket.model.Movie;
import com.paystack.samplemovieticket.model.MovieResponse;
import com.paystack.samplemovieticket.network.EndpointConfig;
import com.paystack.samplemovieticket.network.NetworkConfig;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.paystack.samplemovieticket.utils.Constants.BUNDLE_MOVIE_BACKDROP_URL;
import static com.paystack.samplemovieticket.utils.Constants.BUNDLE_MOVIE_NAME;
import static com.paystack.samplemovieticket.utils.Constants.BUNDLE_MOVIE_OVERVIEW;

public class MainActivity extends AppCompatActivity implements NowPlayingAdapter.MovieAdapterClickListener, UpcomingAdapter.UpcomingAdapterClickListener {

    private List<Movie> movies;
    private NowPlayingAdapter nowPlayingAdapter;
    private UpcomingAdapter upcomingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView mNowPlaying = findViewById(R.id.rv_now_playing);
        RecyclerView mUpcoming = findViewById(R.id.rv_upcoming);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mNowPlaying.setLayoutManager(layoutManager);
        mNowPlaying.setHasFixedSize(true);

        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        mUpcoming.setLayoutManager(layoutManager1);
        mUpcoming.setHasFixedSize(true);

        nowPlayingAdapter = new NowPlayingAdapter(this);
        mNowPlaying.setAdapter(nowPlayingAdapter);
        upcomingAdapter = new UpcomingAdapter(this);
        mUpcoming.setAdapter(upcomingAdapter);

        fetchNowPlaying();
        fetchUpcoming();
    }


    private void fetchNowPlaying() {
        EndpointConfig endpointConfig = NetworkConfig.configureNetwork.create(EndpointConfig.class);
        endpointConfig.getNowPlayingMovies(BuildConfig.MOVIE_DB_API_KEY)
                .enqueue(new Callback<MovieResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                        if(response.body() != null ){
                            parseMovieResponse(response.body());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {
                        Log.d("MainActivity Error: ", t.getMessage());
                    }
                });
    }

    private void fetchUpcoming() {
        EndpointConfig endpointConfig = NetworkConfig.configureNetwork.create(EndpointConfig.class);
        endpointConfig.getUpcomingMovies(BuildConfig.MOVIE_DB_API_KEY)
                .enqueue(new Callback<MovieResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                        if(response.body() != null ){
                            parseUpcomingMovies(response.body());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {
                        Log.d("MainActivity Error: ", t.getMessage());
                    }
                });
    }

    private void parseUpcomingMovies(MovieResponse movieResponse) {
        movies = movieResponse.getResults();
        upcomingAdapter.setMovies(movies);
    }

    private void parseMovieResponse(MovieResponse movieResponse) {
        movies = movieResponse.getResults();
        nowPlayingAdapter.setMovies(movies);
    }

    @Override
    public void movieClicked(int position) {
        String movieName = movies.get(position).getTitle();
        String movieOverview = movies.get(position).getOverview();
        String backdropPath = movies.get(position).getBackdropPath();

       Intent intent = new Intent(this, MovieDetailsActivity.class);
       intent.putExtra(BUNDLE_MOVIE_NAME, movieName);
       intent.putExtra(BUNDLE_MOVIE_OVERVIEW, movieOverview);
       intent.putExtra(BUNDLE_MOVIE_BACKDROP_URL, backdropPath);

       startActivity(intent);
    }
}
