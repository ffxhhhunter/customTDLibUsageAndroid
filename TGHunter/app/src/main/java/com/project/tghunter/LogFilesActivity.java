package com.project.tghunter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class LogFilesActivity extends AppCompatActivity {
    public static Context context;

    private Button buttonGoBack;

    private RecyclerView listLogFiles;

    private ConstraintLayout buttonHomepage;
    private ConstraintLayout buttonSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_logs);

        context = getApplicationContext();

        buttonGoBack = findViewById(R.id.button_back);
        listLogFiles = findViewById(R.id.list_log_files);
        buttonHomepage = findViewById(R.id.homepage_button);
        buttonSettings = findViewById(R.id.settings_button);

        buttonGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLogsActivity();
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

        listLogFiles.setLayoutManager(new LinearLayoutManager(this));
        listLogFiles.setAdapter(new LogFilesAdapter(FileEditor.getFullLogFiles(this), FileEditor.getLogFilesCuted(this)));
    }

    private void startLogsActivity() {
        Intent intent = new Intent(this, LogsActivity.class);
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
        overridePendingTransition(R.drawable.animation_left_right, R.drawable.slide_in_left);
    }
}