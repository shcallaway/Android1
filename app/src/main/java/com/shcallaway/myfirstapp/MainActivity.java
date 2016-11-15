package com.shcallaway.myfirstapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArraySet;
import android.view.View;

import java.util.Date;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    // Declare constant for SharedPreferences file name.
    public static final String PREFS_NAME = "MyPrefsFile";

    // Declare a Set for storing app usage logs.
    Set<String> logs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize SharedPreferences.
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        // Check if logs StringSet already exists in SharedPreferences.
        boolean logsDoesNotExist = (settings.getStringSet("logs", null) == null);

        if (logsDoesNotExist) {
            // Initialize logs with an empty ArraySet.
            logs = new ArraySet<>();
        } else {
            // Or, point logs to existing StringSet in SharedPreferences.
            logs = settings.getStringSet("logs", null);
        }

        // Create a new log String from the current Date.
        Date date = new Date();
        String log = String.valueOf(date.getTime());

        // Add the new log to logs.
        logs.add(log);

        // Create a SharedPreferences Editor.
        SharedPreferences.Editor editor = settings.edit();

        // Update the logs in SharedPreferences to include the new log.
        editor.putStringSet("logs", logs);
        editor.commit();
    }

    public void logActivity(View view) {

        // Intent might not be able to carry ArraySet (find another way)
        // Maybe -- a bundle?

        Intent intent = new Intent(this, LogActivity.class);
        startActivity(intent);
    }

}
