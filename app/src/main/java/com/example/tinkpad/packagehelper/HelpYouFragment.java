package com.example.tinkpad.packagehelper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HelpYouFragment extends Fragment implements Runnable,AdapterView.OnItemClickListener {
    private ListView lv;
    private SimpleAdapter adapter;
    private List<Map<String, Object>> list;
    ImageView mine;
    Handler handler;
    Message msg;
    //Bundle bundle;

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
                Intent submit = new Intent(getActivity(), ShowListActivity.class);
                startActivity(submit);

            }


        });
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 7) {
                    ArrayList<Map<String, Object>> list3 = new ArrayList<Map<String, Object>>();
                    Bundle bundle = msg.getData();
                    ArrayList list = bundle.getParcelableArrayList("list");
                    list3= (ArrayList<Map<String, Object>>) list.get(0);
                    adapter = new SimpleAdapter(getActivity(), list3, R.layout.list_item,
                            new String[]{"com", "num", "date", "code"},
                            new int[]{R.id.list_com, R.id.list_num, R.id.list_date, R.id.list_code});      //配置适配器，并获取对应Item中的ID
                    lv.setAdapter(adapter);
                }
                super.handleMessage(msg);


            }

        };
        Thread t = new Thread(this);
        t.start();
        lv.setOnItemClickListener(this);


    }


    @Override
    public void run() {
        PreparedStatement ps=null;
        Connection con=null;
        ResultSet rs=null;
//获取链接数据库对象
        con= DBConnection.getConnection();
        //MySQL 语句
        ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();


        String sql="SELECT packageNo,companyName,code,deadline from package";
        try {
            boolean closed=con.isClosed();
            if((con!=null)&&(!closed)){

                ps= (PreparedStatement) con.prepareStatement(sql);
                if(ps!=null){
                    rs= ps.executeQuery();
                    if(rs!=null){
                        while(rs.next()){
                            Map<String, Object> map = new HashMap<String, Object>();
                            String com=rs.getString("companyName");
                            String num=rs.getString("code");
                            String no=rs.getString("packageNo");
                            String ddl=rs.getString("deadline");
                            map.put("com", com);
                            map.put("num", num);
                            map.put("date", ddl);
                            map.put("code", no);
                            list.add(map);
                        }

                    }
                }


            }
        } catch (SQLException e) {
            Log.e("DB", "run: " + e.toString());
            e.printStackTrace();
        }
        Message msg=handler.obtainMessage(7);
        Bundle bundle=new Bundle();
        ArrayList list1 = new ArrayList(); //这个list用于在budnle中传递 需要传递的ArrayList<Object>
        list1.add(list);
        bundle.putParcelableArrayList("list",list1);
        msg.setData(bundle);
        handler.sendMessage(msg);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        HashMap<String, String> map = (HashMap<String, String>) lv.getItemAtPosition(position);
        String code = map.get("code");
        Intent show = new Intent(this.getActivity(), ShowListActivity.class);
        show.putExtra("code",code);
        startActivity(show);

    }
}


    /*
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
    }*/



