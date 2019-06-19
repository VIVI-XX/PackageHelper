package com.example.tinkpad.packagehelper;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HelpMeFragment extends Fragment implements Runnable,AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener {
    ImageView add;
    private ListView lv;
    private SimpleAdapter adapter;
    private List<Map<String, Object>> list;
    public final String TAG="HELP";
    String username,tel,sid;
    Handler handler;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_help_me_fragment, container, false);
        lv = (ListView) view.findViewById(R.id.listview_me);	//实例化
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView tv = (TextView) getView().findViewById(R.id.text_helpme_title);
        tv.setText("       Help Me");
        if(getArguments()!=null){
            username=getArguments().getString("user");
            tel=getArguments().getString("tel");
            sid=getArguments().getString("sid");

        }

        add = (ImageView) getActivity().findViewById(R.id.image_helpme);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent submit = new Intent(getActivity(),SubmitActivity.class);
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
                    adapter = new SimpleAdapter(getActivity(), list3, R.layout.list_item1,
                            new String[]{"com", "state", "date", "packageno"},
                            new int[]{R.id.list1_com, R.id.list1_sit, R.id.list1_date, R.id.list1_code});      //配置适配器，并获取对应Item中的ID
                    lv.setAdapter(adapter);
                }
                super.handleMessage(msg);


            }

        };
        Thread t = new Thread(this);
        t.start();
        lv.setOnItemClickListener(this);
        lv.setOnItemLongClickListener(this);
    }
    //数据的获取@！
    /*private List<? extends Map<String, ?>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

//将需要的值传入map中
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", "我的快递1");
        map.put("com", "圆通");
        map.put("date","2019-1-2");
        map.put("situation","未接单");
        map.put("code","1");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "我的快递2");
        map.put("com", "中通");
        map.put("date","2019-1-2");
        map.put("situation","未接单");
        map.put("code","6");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "我的快递3");
        map.put("com", "韵达");
        map.put("date","2019-1-2");
        map.put("situation","未接单");
        map.put("code","8");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "我的快递4");
        map.put("com", "百世");
        map.put("date","2019-1-2");
        map.put("situation","未接单");
        map.put("code","9");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "我的快递5");
        map.put("com", "京东");
        map.put("date","2019-1-2");
        map.put("situation","未接单");
        map.put("code","10");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("title", "我的快递6");
        map.put("com", "圆通");
        map.put("date","2019-1-2");
        map.put("situation","未接单");
        map.put("code","12");
        list.add(map);

        return list;
    }
    */

    @Override
    public void onStart() {
        super.onStart();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 7) {
                    ArrayList<Map<String, Object>> list3 = new ArrayList<Map<String, Object>>();
                    Bundle bundle = msg.getData();
                    ArrayList list = bundle.getParcelableArrayList("list");
                    list3= (ArrayList<Map<String, Object>>) list.get(0);
                    adapter = new SimpleAdapter(getActivity(), list3, R.layout.list_item1,
                            new String[]{"com", "state", "date", "packageno"},
                            new int[]{R.id.list1_com, R.id.list1_sit, R.id.list1_date, R.id.list1_code});      //配置适配器，并获取对应Item中的ID
                    lv.setAdapter(adapter);
                }
                super.handleMessage(msg);


            }

        };
        Thread t = new Thread(this);
        t.start();
        lv.setOnItemClickListener(this);
        lv.setOnItemLongClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        HashMap<String, String> map = (HashMap<String, String>) lv.getItemAtPosition(position);
        String packageno = map.get("packageno");
        //打开新的页面，传入参数
        Intent show = new Intent(this.getActivity(), ShowListActivity2.class);
        show.putExtra("packageno", packageno);


        startActivity(show);


    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        builder.setTitle("提示").setMessage("请确认是否删除当前数据").setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                list.remove(position);
                adapter.notifyDataSetChanged();

            }
        })
                .setNegativeButton("否", null);
        builder.create().show();
        return true;
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


        String sql="SELECT packageNo,companyName,state,deadline from package where studentID='41723062'";
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
                            String state=rs.getString("state");
                            String no=rs.getString("packageNo");
                            String ddl=rs.getString("deadline");
                            map.put("com", com);
                            map.put("state", state);
                            map.put("date", ddl);
                            map.put("packageno", no);
                            list.add(map);
                        }

                    }
                }


            }
        } catch (SQLException e) {
            Log.e("DB", "run: " + e.toString());
            e.printStackTrace();
        }
        DBConnection.closeAll(con,ps);//关闭相关操作
        Message msg=handler.obtainMessage(7);
        Bundle bundle=new Bundle();
        ArrayList list1 = new ArrayList(); //这个list用于在budnle中传递 需要传递的ArrayList<Object>
        list1.add(list);
        bundle.putParcelableArrayList("list",list1);
        msg.setData(bundle);
        handler.sendMessage(msg);


    }



    }



