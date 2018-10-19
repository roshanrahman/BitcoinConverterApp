package com.example.roshan.bitcoinproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class ChooseCurrency extends AppCompatActivity {

    String userName;
    SharedPreferences sharedPreferences;
    TextView helloTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_currency);
        getSupportActionBar().setTitle("Choose currency");
        helloTextView = findViewById(R.id.helloTextView);
        sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        userName = sharedPreferences.getString("username", "undefined");
        helloTextView.setText("Hello, " + userName + "!");
    }

    public void handleCurrencyClick(View view) {
        String selectedCurrency = view.getTag().toString();
        Intent displayActivityIntent = new Intent(ChooseCurrency.this, DisplayActivity.class);
        displayActivityIntent.putExtra("selectedCurrency", selectedCurrency);
        startActivity(displayActivityIntent);
    }
}
