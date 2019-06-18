package com.example.tinkpad.packagehelper;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class HelpYouList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_you_list);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Let Me Help You");

    }
}
