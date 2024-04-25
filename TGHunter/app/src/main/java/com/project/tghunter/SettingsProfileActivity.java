package com.project.tghunter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class SettingsProfileActivity extends AppCompatActivity {
    private TextView profileNumber;

    private Button buttonBack;

    private ConstraintLayout buttonHomepage;
    private ConstraintLayout buttonSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_profile);

        Intent intent = getIntent();
        String data = String.valueOf(intent.getStringExtra("key"));

        profileNumber = findViewById(R.id.profile_number);
        buttonBack = findViewById(R.id.button_back);
        buttonHomepage = findViewById(R.id.homepage_button);
        buttonSettings = findViewById(R.id.settings_button);

        profileNumber.setText("Settings: " + data);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startProfilesActivity();
            }
        });

        buttonHomepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startHomepageActivity();
            }
        });

        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSettingsActivity();
            }
        });
    }

    private void startProfilesActivity() {
        Intent intent = new Intent(this, ProfilesActivity.class);
        startActivity(intent);
        overridePendingTransition(R.drawable.animation_left_right, R.drawable.slide_in_left);
    }

    private void startHomepageActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.drawable.animation_left_right, R.drawable.slide_in_left);
    }

    private void startSettingsActivity() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
        overridePendingTransition(R.drawable.animation_right_left, R.drawable.slide_out_right);
    }
}