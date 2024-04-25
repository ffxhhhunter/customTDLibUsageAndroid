package com.project.tghunter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import org.drinkless.td.libcore.telegram.*;

public class AddProfileActivity extends AppCompatActivity {
    private Context context = this;

    private TDLibUsage tdLibUsage;

    private String phoneCode;
    private String phoneNumber;

    private Button buttonGoBack;
    private Button buttonNext;

    private TextView errorCc;
    private TextView errorPn;
    private TextView errorLog;

    private EditText inputPhoneCode;
    private EditText inputPhoneNumber;

    private ConstraintLayout buttonHomepage;
    private ConstraintLayout buttonSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addprofile);

        buttonGoBack = findViewById(R.id.button_back);
        buttonNext = findViewById(R.id.button_next);
        errorCc = findViewById(R.id.error_cc);
        errorPn = findViewById(R.id.error_pn);
        errorLog = findViewById(R.id.log);
        inputPhoneCode = findViewById(R.id.phone_code);
        inputPhoneNumber = findViewById(R.id.phone_number);
        buttonHomepage = findViewById(R.id.homepage_button);
        buttonSettings = findViewById(R.id.settings_button);

        buttonGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startProfilesActivity();
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next();
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

    private void startVerifyActivity() {
        Intent intent = new Intent(this, VerifyNumberActivity.class);
        intent.putExtra("fullPhoneNumber", "+" + phoneCode + " " + phoneNumber);
        startActivity(intent);
        overridePendingTransition(R.drawable.animation_right_left, R.drawable.slide_out_right);
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

    private void next() {
        errorLog.setText("");

        phoneCode = String.valueOf(inputPhoneCode.getText()).toString();
        phoneNumber = String.valueOf(inputPhoneNumber.getText()).toString();

        String fullPhoneNumber = "+" + phoneCode + phoneNumber;

        if (phoneCode.isEmpty()) {
            errorCc.setText("Invalid country\ncode");
            if (phoneNumber.isEmpty()) {
                errorPn.setText("Invalid phone number");
                return;
            } else {
                errorPn.setText("");
            }
            return;
        } else {
            errorCc.setText("");
            if (phoneNumber.isEmpty()) {
                errorPn.setText("Invalid phone number");
                return;
            } else {
                errorPn.setText("");
            }
        }

        try {
            tdLibUsage = new TDLibUsage(fullPhoneNumber, context, new Client(new TDLibUsage.UpdateHandler(), null, null));
            tdLibUsage.start();
        } catch (Exception e) {
            FileEditor.writeLog(this, String.valueOf(e));
            tdLibUsage.interrupt();
        }

        startVerifyActivity();
    }
}