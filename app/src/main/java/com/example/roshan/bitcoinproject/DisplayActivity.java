package com.example.roshan.bitcoinproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;

public class DisplayActivity extends AppCompatActivity {

    String selectedCurrency;
    LinearLayout loadingIndicator, display;
    TextView valueDisplayTextView, statusDisplayTextView;

    @Override
    protected void onRestart() {
        startActivity(new Intent(DisplayActivity.this, ChooseCurrency.class));
        finish();
        super.onRestart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        selectedCurrency = getIntent().getStringExtra("selectedCurrency");
        getSupportActionBar().setTitle("Conversion rate");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        loadingIndicator = findViewById(R.id.loadingIndicator);
        valueDisplayTextView = findViewById(R.id.valueDisplay);
        display = findViewById(R.id.display);
        statusDisplayTextView = findViewById(R.id.statusDisplayTextView);
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("https://blockchain.info/ticker", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                display.setVisibility(View.VISIBLE);
                loadingIndicator.setVisibility(View.GONE);
                try {
                    JSONObject responseJSON = new JSONObject(new String(responseBody, "UTF-8"));
                    responseJSON = responseJSON.getJSONObject(selectedCurrency);
                    String value = String.valueOf(responseJSON.getDouble("15m"));
                    valueDisplayTextView.setText(value + " " + selectedCurrency);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                statusDisplayTextView.setText(error.getLocalizedMessage());
            }
        });
    }
}
