package com.example.tinkpad.packagehelper;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HelpYouList extends AppCompatActivity implements Runnable,AdapterView.OnItemClickListener {
    ListView lv;
    Handler handler;
    SimpleAdapter adapter;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_you_list);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Let Me Help You");
        lv = (ListView) findViewById(R.id.lv_help);
        id=getIntent().getStringExtra("data");
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 7) {
                    ArrayList<Map<String, Object>> list3 = new ArrayList<Map<String, Object>>();
                    Bundle bundle = msg.getData();
                    ArrayList list = bundle.getParcelableArrayList("list");
                    list3 = (ArrayList<Map<String, Object>>) list.get(0);
                    adapter = new SimpleAdapter(HelpYouList.this, list3, R.layout.list_item,
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


        String sql="SELECT packageNo,companyName,code,deadline,helperId from package where helperId = "+id;
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
    protected void onStart() {
        super.onStart();
        id=getIntent().getStringExtra("data");
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 7) {
                    ArrayList<Map<String, Object>> list3 = new ArrayList<Map<String, Object>>();
                    Bundle bundle = msg.getData();
                    ArrayList list = bundle.getParcelableArrayList("list");
                    list3 = (ArrayList<Map<String, Object>>) list.get(0);
                    adapter = new SimpleAdapter(HelpYouList.this, list3, R.layout.list_item,
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        HashMap<String, String> map = (HashMap<String, String>) lv.getItemAtPosition(position);
        String packageno = map.get("code");
        Intent show = new Intent(HelpYouList.this, ShowListActivity3.class);
        show.putExtra("packageno",packageno);
        startActivity(show);
    }
}
