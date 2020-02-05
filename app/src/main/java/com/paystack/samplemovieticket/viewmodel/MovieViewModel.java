package com.paystack.samplemovieticket.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.paystack.samplemovieticket.model.MovieResponse;
import com.paystack.samplemovieticket.repository.MovieRepository;

public class MovieViewModel extends AndroidViewModel {
    private LiveData<MovieResponse> nowPlaying;

    public MovieViewModel(@NonNull Application application) {
        super(application);

        MovieRepository movieRepository = new MovieRepository(application);
        nowPlaying =  movieRepository.getNowPlaying();
    }

    public LiveData<MovieResponse> getNowPlaying() {
        return nowPlaying;
    }
}
