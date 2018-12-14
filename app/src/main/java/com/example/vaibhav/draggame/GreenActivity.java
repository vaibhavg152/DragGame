package com.example.vaibhav.draggame;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class GreenActivity extends AppCompatActivity {
    //constants
    private static final String TAG = "RedActivity";

    //widgets
    private TextView txtColor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);
        Log.d(TAG, "onCreate: ");

        initialise();
    }

    private void initialise() {
        Log.d(TAG, "initialise: ");

        txtColor = (TextView) findViewById(R.id.txtColor);
        txtColor.setText("You swiped on GREEN color!");
    }
}
