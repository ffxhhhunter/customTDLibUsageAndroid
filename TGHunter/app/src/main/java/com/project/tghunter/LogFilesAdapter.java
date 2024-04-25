package com.project.tghunter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LogFilesAdapter extends RecyclerView.Adapter<LogFilesAdapter.LogFileHolder> {
    private String[] fullFileNames;
    private String[] fileNamesCuted;

    public LogFilesAdapter(List<String> fullFileNames, List<String> fileNamesCuted) {
        this.fullFileNames = getArray(fullFileNames);
        this.fileNamesCuted = getArray(fileNamesCuted);
    }

    @NonNull
    @Override
    public LogFilesAdapter.LogFileHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.log_file_item, parent, false);
        return new LogFilesAdapter.LogFileHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LogFilesAdapter.LogFileHolder holder, int position) {
        String fullName = fullFileNames[position];
        String fileNameCuted = fileNamesCuted[position];

        holder.bind(fullName, fileNameCuted);
    }

    @Override
    public int getItemCount() {
        return fileNamesCuted.length;
    }

    public class LogFileHolder extends RecyclerView.ViewHolder {
        private TextView logFileName;

        private Button buttonDelete;

        public LogFileHolder(View view) {
            super(view);
            logFileName = view.findViewById(R.id.file_name);
            buttonDelete = view.findViewById(R.id.button_delete);
        }

        public void bind(String fullName, String fileNameCuted) {
            logFileName.setText(fileNameCuted);

            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FileEditor.deleteFile(LogFilesActivity.context, fullName);
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
