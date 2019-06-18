package com.example.tinkpad.packagehelper;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HelpYouList extends AppCompatActivity {
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_you_list);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Let Me Help You");
        lv = (ListView)findViewById(R.id.lv_help);

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("com", "圆通");
        map.put("num", "5-8-155");
        map.put("date","2019-1-2");
        map.put("code","编号：1");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("com", "韵达");
        map.put("num", "2-56-155");
        map.put("date", "2019-3-6");
        map.put("code","编号：2");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("com", "韵达");
        map.put("num", "2-56-155");
        map.put("date", "2019-3-6");
        map.put("code","编号：2");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("com", "韵达");
        map.put("num", "2-56-155");
        map.put("date", "2019-3-6");
        map.put("code","编号：2");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("com", "韵达");
        map.put("num", "2-56-155");
        map.put("date", "2019-3-6");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("com", "韵达");
        map.put("num", "2-56-155");
        map.put("date", "2019-3-6");
        map.put("code","编号：2");
        list.add(map);

    }
}
