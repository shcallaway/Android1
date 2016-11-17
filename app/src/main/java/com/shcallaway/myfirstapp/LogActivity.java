package com.shcallaway.myfirstapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class LogActivity extends AppCompatActivity {

    ArrayList<String> logArray = new ArrayList<>();
    ListView listView;
    String logs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        // Retrieve logs from the Bundle.
        Bundle bundle = getIntent().getExtras();
        logs = bundle.getString("logs");

        // Populate logArray with timestamps.
        populateLogArrayFromLogs();

        // Render the values from logArray in the view.
        listView = (ListView) findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, logArray);
        listView.setAdapter(adapter);
    }

    private void populateLogArrayFromLogs() {

        StringBuilder logBuilder = new StringBuilder("");

        // Iterate over each character in the logs String.
        for (char ch : logs.toCharArray()) {
            // If the character is a delimiter...
            if (ch == ',') {
                // Capture the subString...
                String utcTime = logBuilder.toString();
                // Convert it to local time...
                String localTime = convertUtcToLocalTime(utcTime);
                // Add it to the logArray...
                logArray.add(localTime);
                // And re-initialize the logBuilder.
                logBuilder = new StringBuilder("");
            } else {
                // Otherwise, keep building!
                logBuilder.append(ch);
            }
        }
    }

    private String convertUtcToLocalTime(String utcTime) {

        // Set up a SimpleDateFormat object with the same format we used earlier.
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

        // Declare a variable to contain the output value.
        // Also, initialize, just in case try-catch fails -- not ideal.
        Date localTime = new Date();

        // Try to parse a Date object from the utcTime String.
        try {
            // Assign the new Date to localTime.
            localTime = sdf.parse(utcTime);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Return the String version of localTime.
        return localTime.toString();
    }
}

// Wrap "logging" in logic: whether or not instance state bundle was changed (if null, app was just started)