package com.project.tghunter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {
    private String[] profileNames;
    private String[] profileIds;
    private String[] profileIsActive;

    private OnLoginClickListener onLoginClickListener;
    private StartSettings startSettings;

    public ProfileAdapter(List<String> profileNames, List<String> profileIds, List<String> profileIsActive) {
        this.profileNames = getArray(profileNames);
        this.profileIds = getArray(profileIds);
        this.profileIsActive = getArray(profileIsActive);
    }

    public void setOnLoginClickListener(OnLoginClickListener listener) {
        this.onLoginClickListener = listener;
    }

    public void setStartSettings(StartSettings listener) {
        this.startSettings = listener;
    }

    @Override
    public ProfileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_rectangle, parent, false);
        return new ProfileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProfileViewHolder holder, int position) {
        String profileName = profileNames[position];
        holder.bind(position + 1, profileName);
    }

    @Override
    public int getItemCount() {
        return profileNames.length;
    }

    public class ProfileViewHolder extends RecyclerView.ViewHolder {
        private TextView number;
        private TextView name;
        private Button loginButton;
        private Button settingsButton;

        public ProfileViewHolder(View itemView) {
            super(itemView);
            number = itemView.findViewById(R.id.profile_number);
            name = itemView.findViewById(R.id.profile_name);
            loginButton = itemView.findViewById(R.id.login_button);
            settingsButton = itemView.findViewById(R.id.settings_button);
        }

        public void bind(int number, String profileName) {
            this.number.setText(String.valueOf(number));
            this.name.setText(profileName);

            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onLoginClickListener.onLoginClick(String.valueOf(number));
                }
            });

            settingsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startSettings.start(String.valueOf(number));
                }
            });
        }
    }

    private String[] getArray(List<String> list) {
        String[] array = new String[list.size()];

        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }

        return array;
    }
}
