package com.project.tghunter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button buttonProfiles;
    private Button buttonParseData;
    private Button buttonMassJoin;
    private Button buttonMassView;
    private Button buttonMassReact;
    private Button buttonSpam;

    private ConstraintLayout buttonSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonProfiles = findViewById(R.id.button_profiles);
        buttonParseData = findViewById(R.id.button_parsedata);
        buttonMassJoin = findViewById(R.id.button_massjoin);
        buttonMassView = findViewById(R.id.button_massview);
        buttonMassReact = findViewById(R.id.button_massreact);
        buttonSpam = findViewById(R.id.button_spam);
        buttonSettings = findViewById(R.id.settings_button);

        buttonProfiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startProfilesActivity();
            }
        });

        buttonParseData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startParsedataActivity();
            }
        });

        buttonMassJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMassjoinActivity();
            }
        });

        buttonMassView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMassviewActivity();
            }
        });

        buttonMassReact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMassreactActivity();
            }
        });

        buttonSpam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSpamActivity();
            }
        });

        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSettingsActivity();
            }
        });

        FileEditor.writeLog(this, "Successfully started program (opened main panel)");
    }

    private void startProfilesActivity() {
        Intent intent = new Intent(this, ProfilesActivity.class);
        startActivity(intent);
        overridePendingTransition(R.drawable.animation_right_left, R.drawable.slide_out_right);
    }

    private void startParsedataActivity() {
        Intent intent = new Intent(this, ParsedataActivity.class);
        startActivity(intent);
        overridePendingTransition(R.drawable.animation_right_left, R.drawable.slide_out_right);
    }

    private void startMassjoinActivity() {
        Intent intent = new Intent(this, MassjoinActivity.class);
        startActivity(intent);
        overridePendingTransition(R.drawable.animation_right_left, R.drawable.slide_out_right);
    }

    private void startMassviewActivity() {
        Intent intent = new Intent(this, MassviewActivity.class);
        startActivity(intent);
        overridePendingTransition(R.drawable.animation_right_left, R.drawable.slide_out_right);
    }

    private void startMassreactActivity() {
        Intent intent = new Intent(this, MassreactActivity.class);
        startActivity(intent);
        overridePendingTransition(R.drawable.animation_right_left, R.drawable.slide_out_right);
    }

    private void startSpamActivity() {
        Intent intent = new Intent(this, SpamActivity.class);
        startActivity(intent);
        overridePendingTransition(R.drawable.animation_right_left, R.drawable.slide_out_right);
    }

    private void startSettingsActivity() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
        overridePendingTransition(R.drawable.animation_right_left, R.drawable.slide_out_right);
    }
}