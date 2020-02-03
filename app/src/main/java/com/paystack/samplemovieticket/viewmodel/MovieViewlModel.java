package com.paystack.samplemovieticket.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.paystack.samplemovieticket.model.MovieResponse;

public class MovieViewlModel extends AndroidViewModel {
    private LiveData<MovieResponse> nowPlaying;

    public MovieViewlModel(@NonNull Application application) {
        super(application);
    }
}
