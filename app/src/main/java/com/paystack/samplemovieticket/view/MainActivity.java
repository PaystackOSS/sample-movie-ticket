package com.paystack.samplemovieticket.view;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.paystack.samplemovieticket.BuildConfig;
import com.paystack.samplemovieticket.R;
import com.paystack.samplemovieticket.model.MovieResponse;
import com.paystack.samplemovieticket.network.EndpointConfig;
import com.paystack.samplemovieticket.network.NetworkConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*        Log.d("MainActivity: ", "onCreate: ");

        MovieViewModel movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        movieViewModel.getNowPlaying().observe(this, movies -> {
            Log.d("API Call: ", "Done!!!");
        });*/
        fetchNowPlaying();
    }


    private void fetchNowPlaying() {
        Log.d("MainActivity: ", "fetchNowPlaying: " + BuildConfig.MOVIE_DB_API_KEY);
        EndpointConfig endpointConfig = NetworkConfig.configureNetwork.create(EndpointConfig.class);
        endpointConfig.getNowPlayingMovies(BuildConfig.MOVIE_DB_API_KEY)
                .enqueue(new Callback<MovieResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                        Log.d("MainActivity Response", response.code() + "---");
                        if(response.body() != null ){
                            Log.d("Response", response.body().toString());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {
                        Log.d("MainActivity Error: ", t.getMessage());
                    }
                });
    }
}
