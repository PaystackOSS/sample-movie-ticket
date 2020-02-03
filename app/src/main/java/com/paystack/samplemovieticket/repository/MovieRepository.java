package com.paystack.samplemovieticket.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.paystack.samplemovieticket.model.MovieResponse;
import com.paystack.samplemovieticket.network.EndpointConfig;
import com.paystack.samplemovieticket.network.NetworkConfig;

public class MovieRepository {
    private LiveData<MovieResponse> nowPlaying;
    private Application application;

    public MovieRepository(Application application) {
        this.application = application;
    }


    private void fetchNowPlaying() {
        EndpointConfig endpointConfig = NetworkConfig.configureNetwork.create(EndpointConfig.class);
        endpointConfig.getNowPlayingMovies()
    }
}
