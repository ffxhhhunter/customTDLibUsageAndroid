package com.project.tghunter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class LogsActivity extends AppCompatActivity {
    private Button buttonBack;
    private Button buttonManageLogFiles;
    private Button buttonDeleteLogFile;

    private ConstraintLayout buttonHomepage;
    private ConstraintLayout buttonSettings;

    private TextView logFileName;
    private TextView logs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logs);

        buttonBack = findViewById(R.id.button_back);
        buttonManageLogFiles = findViewById(R.id.button_manage_log_files);
        buttonDeleteLogFile = findViewById(R.id.button_delete_log_file);
        buttonHomepage = findViewById(R.id.homepage_button);
        buttonSettings = findViewById(R.id.settings_button);
        logFileName = findViewById(R.id.log_file_name);
        logs = findViewById(R.id.logs);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });

        buttonManageLogFiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startManageLogFilesActivity();
            }
        });

        buttonDeleteLogFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteLogFile();
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
                back();
            }
        });

        String fileName = FileEditor.defaultLogFileName;
        String log = FileEditor.readLog(this, FileEditor.defaultLogFileName);

        logFileName.setText("Current log file: \n" + fileName);

        if (log.equals("")) {
            logs.setText("There is no any log");
        } else {
            logs.setText(log);
        }
    }

    private void deleteLogFile() {
        FileEditor.eraseLog(this);
    }

    private void startManageLogFilesActivity() {
        Intent intent = new Intent(this, LogFilesActivity.class);
        startActivity(intent);
        overridePendingTransition(R.drawable.animation_right_left, R.drawable.slide_out_right);
    }

    private void startHomepageActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.drawable.animation_left_right, R.drawable.slide_in_left);
    }

    private void back() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
        overridePendingTransition(R.drawable.animation_left_right, R.drawable.slide_in_left);
    }
}