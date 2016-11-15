package com.shcallaway.myfirstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

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

        populateLogArrayFromLogs();

        listView = (ListView) findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, logArray);
        listView.setAdapter(adapter);
    }

    private void populateLogArrayFromLogs() {
        StringBuilder logBuilder = new StringBuilder("");

        for (char ch : logs.toCharArray()) {
            if (ch == ',') {
                String unformattedLog = logBuilder.toString();
                String formattedLog = formatLog(unformattedLog);
                logArray.add(formattedLog);
                logBuilder = new StringBuilder("");
            } else {
                logBuilder.append(ch);
            }
        }
    }

    private String formatLog(String unformattedLog) {
        String formattedLog;

        Date logDate = new Date(Long.parseLong(unformattedLog));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z", Locale.US);
        formattedLog = sdf.format(logDate);

        // Do some magic to format the unformatted String...
        return formattedLog;
    }
}

// Make sure you can change timezone when reloading list activity, times change
// wrap logging in logic: whether or not instance state bundle was changed (if null, app was just started)