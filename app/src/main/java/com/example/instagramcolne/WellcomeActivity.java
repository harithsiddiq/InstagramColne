package com.example.instagramcolne;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class WellcomeActivity extends AppCompatActivity {

    TextView wllcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellcome);

        Intent i = getIntent();
        String name = i.getStringExtra("name");
        wllcome = findViewById(R.id.wellcome);
        wllcome.setText("Wellcome " + name);
    }
}
