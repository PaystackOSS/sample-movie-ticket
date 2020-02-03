package com.paystack.samplemovieticket.network;

import com.paystack.samplemovieticket.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EndpointConfig {
    @GET("/movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey,
                                          @Query("language") String language,
                                          @Query("page") String page);

    @GET("/movie/upcoming")
    Call<MovieResponse> getUpcomingMovies(@Query("api_key") String apiKey,
                                          @Query("language") String language,
                                          @Query("page") String page);


    @GET("/movie/now_playing")
    Call<MovieResponse> getNowPlayingMovies(@Query("api_key") String apiKey,
                                          @Query("language") String language,
                                          @Query("page") String page);
}
