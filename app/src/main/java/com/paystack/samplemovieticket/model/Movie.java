package com.paystack.samplemovieticket.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Movie {
    public int id;
    public String title;
    @SerializedName("poster_path")
    @Expose
    public String posterPath;
    @SerializedName("vote_average")
    @Expose
    public double voteAverage;
    public String overview;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public String getOverview() {
        return overview;
    }
}
