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
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        getActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String movieName = intent.getStringExtra(BUNDLE_MOVIE_NAME);
        String movieOverview = intent.getStringExtra(BUNDLE_MOVIE_OVERVIEW);
        String movieBackdropPath = intent.getStringExtra(BUNDLE_MOVIE_BACKDROP_URL);

        TextView mMovieName = findViewById(R.id.tv_movie_details_name);
        TextView mMovieOverview = findViewById(R.id.tv_movie_details_overview);
        ImageView mMoviePoster = findViewById(R.id.iv_movie_details_image);

        mMovieName.setText(movieName);
        mMovieOverview.setText(movieOverview);

        if(movieBackdropPath.length() <= 0) {
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

        initializePaystack();

        Button button = findViewById(R.id.btn_buy_ticket);
        button.setOnClickListener(v -> performCharge());
    }

    private void initializePaystack() {
        PaystackSdk.initialize(getApplicationContext());
        PaystackSdk.setPublicKey(BuildConfig.PSTK_PUBLIC_KEY);
    }

    // This is the subroutine you will call after creating the charge
    // adding a card and setting the access_code
    public void performCharge(){
        //create a Charge object
        // This sets up the card and check for validity
        // This is a test card from paystack
        String cardNumber = "4084084084084081";
        int expiryMonth = 11; //any month in the future
        int expiryYear = 20; // any year in the future. '2018' would work also!
        String cvv = "408";  // cvv of the test card

        Card card = new Card(cardNumber, expiryMonth, expiryYear, cvv);

        Charge charge = new Charge();
        charge.setAmount(10000);
        charge.setEmail("customer@email.com");
        charge.setCard(card); //sets the card to charge

        Toast.makeText(this, "Test", Toast.LENGTH_LONG).show();

        PaystackSdk.chargeCard(this, charge, new Paystack.TransactionCallback() {
            @Override
            public void onSuccess(Transaction transaction) {
                // This is called only after transaction is deemed successful.
                // Retrieve the transaction, and send its reference to your server
                // for verification.
                Log.d("Main Activity", "onSuccess: " + transaction.getReference());
                parseResponse(transaction.getReference());
            }

            @Override
            public void beforeValidate(Transaction transaction) {
                // This is called only before requesting OTP.
                // Save reference so you may send to server. If
                // error occurs with OTP, you should still verify on server.
                Log.d("Main Activity", "beforeValidate: " + transaction.getReference());
            }

            @Override
            public void onError(Throwable error, Transaction transaction) {
                //handle error here
                Log.d("Main Activity", "onError: " + error.getLocalizedMessage());
            }

        });
    }

    private void parseResponse(String transactionReference) {
        Toast.makeText(this, transactionReference, Toast.LENGTH_LONG).show();
    }
}
