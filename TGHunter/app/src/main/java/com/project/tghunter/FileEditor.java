package com.project.tghunter;

import android.content.Context;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileEditor {
    public static String defaultLogFileName = "log.txt";

    public static String readLog(Context context, String fileName) {
        StringBuilder sb = new StringBuilder();

        try (FileInputStream fis = new FileInputStream(context.getApplicationContext().getFilesDir() + fileName)) {

            int e;

            while ((e = fis.read()) != -1) {
                sb.append((char) e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    public static void writeLog(Context context, String text) {
        String stockLog = readLog(context, defaultLogFileName);

        try (FileOutputStream fos = new FileOutputStream(context.getApplicationContext().getFilesDir() + defaultLogFileName)) {
            if (stockLog.equals("")) {
                Date date = new Date();
                String d = "[" + date + "] ";

                fos.write(d.getBytes());
                fos.write(text.getBytes());
                fos.write("\n\n".getBytes());
            } else {
                Date date = new Date();
                String d = "[" + date + "] ";

                fos.write(stockLog.getBytes());
                fos.write(d.getBytes());
                fos.write(text.getBytes());
                fos.write("\n\n".getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "Cannot write log in " + defaultLogFileName, Toast.LENGTH_LONG).show();
        }
    }

    public static void eraseLog(Context context) {
        try (FileOutputStream fos = new FileOutputStream(context.getApplicationContext().getFilesDir() + "log.txt")) {
            fos.write("".getBytes());
            Toast.makeText(context, "Successfully erased file: " + defaultLogFileName, Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "Cannot erase log in " + defaultLogFileName, Toast.LENGTH_LONG).show();
        }
    }

    public static List<String> getFullLogFiles(Context context) {
        File[] files = context.getApplicationContext().getFilesDir().listFiles();

        List<String> fullFileNames = new ArrayList<>();

        for (int i = 0; i < files.length; i++) {
            if (files[i].getName().startsWith("log") && files[i].getName().endsWith(".txt")) {
                fullFileNames.add(context.getApplicationContext().getFilesDir() + files[i].getName());
            }
        }

        return fullFileNames;
    }

    public static List<String> getLogFilesCuted(Context context) {
        File[] files = context.getApplicationContext().getFilesDir().listFiles();

        List<String> fileNamesCuted = new ArrayList<>();

        for (int i = 0; i < files.length; i++) {
            if (files[i].getName().startsWith("log") && files[i].getName().endsWith(".txt")) {
                fileNamesCuted.add(files[i].getName());
            }
        }

        return fileNamesCuted;
    }

    public static void deleteFile(Context context, String fileName) {
        File file = new File(fileName);

        if (file.exists()) {
            if (file.delete()) {
                Toast.makeText(context, "File successfully deleted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, "Cannot delete file", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(context, "File " + fileName + " isn't exists", Toast.LENGTH_LONG).show();
        }
    }
}
