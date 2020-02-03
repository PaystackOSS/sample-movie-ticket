package com.paystack.samplemovieticket.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieResponse {
    public int page;
    @SerializedName("total_results")
    @Expose
    public int totalResults;
    @SerializedName("total_pages")
    @Expose
    public int totalPages;
    public Movie[] results;

    public int getPage() {
        return page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public Movie[] getResults() {
        return results;
    }
}
