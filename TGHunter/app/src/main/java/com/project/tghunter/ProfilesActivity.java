package com.project.tghunter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ProfilesActivity extends AppCompatActivity {
    private TextView profilesCount;

    private Button buttonGoBack;
    private Button buttonAddProfile;

    private ConstraintLayout buttonHomepage;
    private ConstraintLayout buttonSettings;

    private LinearLayoutManager linearLayoutManager;

    private RecyclerView recyclerView;

    private ProfileAdapter profileAdapter;

    private List<String> profileNames;
    private List<String> profileIds;
    private List<String> profileIsActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiles);

        buttonGoBack = findViewById(R.id.button_back);
        profilesCount = findViewById(R.id.profiles_count);
        buttonAddProfile = findViewById(R.id.add_profile);
        recyclerView = findViewById(R.id.list_profiles);
        buttonHomepage = findViewById(R.id.homepage_button);
        buttonSettings = findViewById(R.id.settings_button);

        linearLayoutManager = new LinearLayoutManager(this);
        profileAdapter = loadProfilesAdapter();

        buttonGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startHomepageActivity();
            }
        });

        buttonAddProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAddProfileActivity();
            }
        });

        profileAdapter.setOnLoginClickListener(new OnLoginClickListener() {
            @Override
            public void onLoginClick(String number) {
                loginToAccount(number);
            }
        });

        profileAdapter.setStartSettings(new StartSettings() {
            @Override
            public void start(String number) {
                startSettingsProfile(number);
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

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(profileAdapter);
    }

    private ProfileAdapter loadProfilesAdapter() {
        List<String> linesFromFile = loadLinesFromFile(this);

        profileNames = new ArrayList<>();
        profileIds = new ArrayList<>();
        profileIsActive = new ArrayList<>();

        if (linesFromFile.size() == 0) {
            profilesCount.setText("You don't have any profile");
        } else {
            profilesCount.setText("Your profiles:");

            for (int i = 0; i < linesFromFile.size(); i++) {
                if (linesFromFile.get(i).contains("Profile")) {
                    String line = linesFromFile.get(i);

                    String cutedLine = line.substring(8, line.length() - 1);

                    String[] keyValuePairs = cutedLine.split(", ");

                    for (int a = 0; a < keyValuePairs.length; a++) {
                        String[] pair = keyValuePairs[a].split(": ");

                        if (pair.length == 2) {
                            String key = pair[0];
                            String value = pair[1];

                            switch (key) {
                                case "name":
                                    profileNames.add(value);
                                    break;
                                case "id":
                                    profileIds.add(value);
                                    break;
                                case "isActive":
                                    profileIsActive.add(value);
                                    break;
                            }
                        }
                    }
                }
            }
        }

        return new ProfileAdapter(profileNames, profileIds, profileIsActive);
    }

    private List<String> loadLinesFromFile(Context context) {
        List<String> lines = new ArrayList<>();

        try {
            InputStream inputStream = context.getResources().openRawResource(R.raw.information);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }

            bufferedReader.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            FileEditor.writeLog(this, String.valueOf(e));
        }

        return lines;
    }

    private void startAddProfileActivity() {
        Intent intent = new Intent(this, AddProfileActivity.class);
        startActivity(intent);
        overridePendingTransition(R.drawable.animation_right_left, R.drawable.slide_out_right);
    }

    private void loginToAccount(String number) {
        Intent intent = new Intent(this, AccountActivity.class);
        intent.putExtra("key", number);
        startActivity(intent);
        overridePendingTransition(R.drawable.animation_right_left, R.drawable.slide_out_right);
    }

    private void startSettingsProfile(String number) {
        Intent intent = new Intent(this, SettingsProfileActivity.class);
        intent.putExtra("key", number);
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
}
