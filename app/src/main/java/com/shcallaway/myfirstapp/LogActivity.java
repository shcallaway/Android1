package com.shcallaway.myfirstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class LogActivity extends AppCompatActivity {

    ArrayList<String> listValues = new ArrayList<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        // Get String version of logs from Intent
        // Convert String version back to ArraySet
        // Use SimpleDateFormat to format Strings as readable Dates
        // Get pretty Dates as Strings; add them to listValues

        listView = (ListView) findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, listValues);
        listView.setAdapter(adapter);
    }
}
