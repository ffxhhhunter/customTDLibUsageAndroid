package com.project.tghunter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class AccountActivity extends AppCompatActivity {
    private TextView accountId;

    private Button buttonGoBack;
    private ConstraintLayout buttonHomepage;
    private ConstraintLayout buttonSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        accountId = findViewById(R.id.account_id);

        buttonGoBack = findViewById(R.id.button_back);
        buttonHomepage = findViewById(R.id.homepage_button);
        buttonSettings = findViewById(R.id.settings_button);

        Intent intent = getIntent();
        String receivedData = intent.getStringExtra("key");
        accountId.setText("Account: " + receivedData);

        buttonGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
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

    private void goBack() {
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