package com.paystack.samplemovieticket.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.paystack.samplemovieticket.BuildConfig;
import com.paystack.samplemovieticket.R;
import com.squareup.picasso.Picasso;

import co.paystack.android.Paystack;
import co.paystack.android.PaystackSdk;
import co.paystack.android.Transaction;
import co.paystack.android.model.Card;
import co.paystack.android.model.Charge;

import static com.paystack.samplemovieticket.utils.Constants.BUNDLE_MOVIE_BACKDROP_URL;
import static com.paystack.samplemovieticket.utils.Constants.BUNDLE_MOVIE_NAME;
import static com.paystack.samplemovieticket.utils.Constants.BUNDLE_MOVIE_OVERVIEW;
import static com.paystack.samplemovieticket.utils.Constants.IMAGE_BASE_URL;

public class MovieDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        assert getSupportActionBar() != null;

        Intent intent = getIntent();
        String movieName = intent.getStringExtra(BUNDLE_MOVIE_NAME);
        String movieOverview = intent.getStringExtra(BUNDLE_MOVIE_OVERVIEW);
        String movieBackdropPath = intent.getStringExtra(BUNDLE_MOVIE_BACKDROP_URL);

        TextView mMovieName = findViewById(R.id.tv_movie_details_name);
        TextView mMovieOverview = findViewById(R.id.tv_movie_details_overview);
        ImageView mMoviePoster = findViewById(R.id.iv_movie_details_image);

        mMovieName.setText(movieName);
        mMovieOverview.setText(movieOverview);

        if( movieBackdropPath != null && movieBackdropPath.length() <= 0) {
            Picasso.with(this)
                    .load(R.drawable.ic_launcher_background)
                    .into(mMoviePoster);
        } else {
            movieBackdropPath = IMAGE_BASE_URL + movieBackdropPath;
            Picasso.with(this)
                    .load(movieBackdropPath)
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .fit()
                    .centerCrop()
                    .into(mMoviePoster);
        }

        Button button = findViewById(R.id.btn_buy_ticket);
        button.setOnClickListener(v -> startActivity(new Intent(MovieDetailsActivity.this, PaymentActivity.class)));
    }
}
