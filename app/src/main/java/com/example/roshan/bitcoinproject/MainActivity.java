package com.example.roshan.bitcoinproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String userName;
    SharedPreferences sharedPreferences;
    EditText userNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Bitcoin Conversion App");
        userNameEditText = findViewById(R.id.userNameEditText);
        sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        userName = sharedPreferences.getString("username", "undefined");
        if(!userName.trim().equals("undefined")) {
            startActivity(new Intent(MainActivity.this, ChooseCurrency.class));
            finish();
        }
    }

    public void handleContinueClick(View view) {
        String userText = userNameEditText.getText().toString().trim();
        if(userText.equals("")) {
            userNameEditText.setError("Please enter your name");
            return;
        } else if(userText.toLowerCase().equals("undefined")) {
            userNameEditText.setError("You cannot use that name");
            return;
        }
        SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
        sharedPreferencesEditor.putString("username", userText);
        sharedPreferencesEditor.commit();
        Toast.makeText(this, "Successfully registered your name!", Toast.LENGTH_SHORT).show();
        recreate();
    }

}
