package com.example.tinkpad.packagehelper;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HelpYouFragment extends Fragment {
    private ListView lv;
    private SimpleAdapter adapter;
    private List<Map<String, Object>> list;
    ImageView mine;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_help_you_fragment, container, false);
        lv = (ListView) view.findViewById(R.id.listview_you);	//实例化
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mine = (ImageView) getActivity().findViewById(R.id.image_helpyou);
        mine.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent submit = new Intent(getActivity(),ShowListActivity.class);
                startActivity(submit);

            }


        });
        adapter = new SimpleAdapter(getActivity(), getData(), R.layout.list_item,
                new String[]{"com", "num", "date","code"},
                new int[]{R.id.list_com, R.id.list_num, R.id.list_date,R.id.list_code});      //配置适配器，并获取对应Item中的ID
        lv.setAdapter(adapter);
    }
    //数据的获取@！
    private List<? extends Map<String, ?>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

//将需要的值传入map中
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

        return list;
    }


}
