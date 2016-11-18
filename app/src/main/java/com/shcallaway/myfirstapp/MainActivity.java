package com.shcallaway.myfirstapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "MyPrefsFile";
    String logs;
    SharedPreferences settings;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize SharedPreferences.
        settings = getSharedPreferences(PREFS_NAME, 0);

        // Populate logs from SharedPreferences.
        populateLogsFromPreferences();

        // Add a new log to logs for this instance.
        addNewLogToLogs();

        // Update set in SharedPreferences to match.
        updateLogsInPreferences();
    }

    public void goToLogActivity(View view) {
        Intent intent = new Intent(this, LogActivity.class);
        intent.putExtra("logs", logs);
        startActivity(intent);
    }

    private void populateLogsFromPreferences() {
        // Get logs from SharedPreferences.
        String logsFromPreferences = settings.getString("logs", null);

        // If logs exist, populate logs using logsFromPreferences.
        if (logsFromPreferences != null) {
            logs = logsFromPreferences;
        // Otherwise, initialize logs as an empty String.
        } else {
            logs = "";
        }
    }

    private void updateLogsInPreferences() {
        // Initialize the Editor.
        editor = settings.edit();

        // Replace old String in SharedPreferences with new one.
        editor.putString("logs", logs);
        editor.commit();
    }

    private String getCurrentDateString() {
        // Get the current DateTime and format it.
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

        return sdf.format(new Date());
    }

    private void addNewLogToLogs() {
        // Concatenate new log to logs String.
        logs += getCurrentDateString() + ",";
    }
}
