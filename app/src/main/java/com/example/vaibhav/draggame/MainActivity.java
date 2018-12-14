package com.example.vaibhav.draggame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private TextView tvDuckOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDuckOff = (TextView) findViewById(R.id.txtDuckOff);
        tvDuckOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: ");

                Intent intent = new Intent(MainActivity.this, DragActivity.class);
                startActivity(intent);
//                finish();
            }
        });
    }
}
