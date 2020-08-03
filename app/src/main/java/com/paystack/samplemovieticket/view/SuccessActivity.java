package com.paystack.samplemovieticket.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.paystack.samplemovieticket.R;

public class SuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        Button button = findViewById(R.id.btn_continue);
        button.setOnClickListener(v -> startActivity(new Intent(SuccessActivity.this, MainActivity.class)));
    }
}