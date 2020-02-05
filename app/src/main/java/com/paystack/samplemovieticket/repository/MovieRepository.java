package com.paystack.samplemovieticket.repository;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.paystack.samplemovieticket.BuildConfig;
import com.paystack.samplemovieticket.model.MovieResponse;
import com.paystack.samplemovieticket.network.EndpointConfig;
import com.paystack.samplemovieticket.network.NetworkConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    private LiveData<MovieResponse> nowPlaying;
    private Application application;

    public MovieRepository(Application application) {
        this.application = application;
    }

    public LiveData<MovieResponse> getNowPlaying() {
//        MutableLiveData<MovieResponse> mutableLiveData = new MutableLiveData<>();

        fetchNowPlaying();
//        mutableLiveData = null;

        return nowPlaying;
    }


    private void fetchNowPlaying() {
        EndpointConfig endpointConfig = NetworkConfig.configureNetwork.create(EndpointConfig.class);
        endpointConfig.getNowPlayingMovies(BuildConfig.MOVIE_DB_API_KEY)
                .enqueue(new Callback<MovieResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                        if(response.body() != null ){
                            Log.d("Response", response.body().toString());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {
                        Log.d("Error: ", t.getMessage());
                    }
                });
    }
}
