package com.example.assignment1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AboutMe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);

        // Home button
        ImageView btnHome3 = findViewById(R.id.btnHome3);
        btnHome3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutMe.this, MainActivity.class);
                startActivity(intent);
            }
        });


        // GitHub button
        Button githubButton = findViewById(R.id.github);
        githubButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/symmrsln/Assignment"));
                startActivity(browserIntent);
            }
        });
    }
}