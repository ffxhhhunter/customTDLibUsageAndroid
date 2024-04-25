package com.project.tghunter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import org.drinkless.td.libcore.telegram.TdApi;

public class VerifyNumberActivity extends AppCompatActivity {
    public static String queryMessage;

    private String fullPhoneNumber;

    private Button buttonGoBack;
    private Button buttonVerify;

    private TextView textTitle;
    private TextView errorWc;

    private EditText verifyingCode;

    private ConstraintLayout buttonHomepage;
    private ConstraintLayout buttonSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sended_code);

        buttonGoBack = findViewById(R.id.button_back);
        buttonVerify = findViewById(R.id.button_verify);
        textTitle = findViewById(R.id.text_title);
        errorWc = findViewById(R.id.error_wc);
        verifyingCode = findViewById(R.id.verifying_code);
        buttonHomepage = findViewById(R.id.homepage_button);
        buttonSettings = findViewById(R.id.settings_button);

        buttonGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });

        buttonVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verify();
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

        Intent intent = getIntent();
        fullPhoneNumber = intent.getStringExtra("fullPhoneNumber");
        textTitle.setText("We're send the code yo your number:\n" + fullPhoneNumber);
    }

    private void verify() {
        String inputCode = String.valueOf(verifyingCode.getText()).toString();

        if (inputCode.isEmpty()) {
            errorWc.setText("Invalid code");
            return;
        } else {
            errorWc.setText("");

            TDLibUsage.verificationCode = inputCode;
        }
    }

    private void back() {
        Intent intent = new Intent(this, AddProfileActivity.class);
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