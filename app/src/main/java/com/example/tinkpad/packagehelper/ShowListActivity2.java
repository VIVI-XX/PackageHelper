package com.example.tinkpad.packagehelper;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShowListActivity2 extends AppCompatActivity implements Runnable {
    Button btn_home;
    String com,name,weight,urgent,address,ddl,size,num,price,packageno,tel,state;
    Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list2);
        packageno = getIntent().getStringExtra("packageno");

        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
        btn_home=(Button) findViewById(R.id.show2_home);
        btn_home.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();
            }
        });

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 7) {
                    Bundle bundle=msg.getData();
                    ((TextView)findViewById(R.id.show2_situation)).setText(bundle.getString("state"));
                    ((TextView)findViewById(R.id.show2_com)).setText(bundle.getString("com"));
                    ((TextView)findViewById(R.id.show2_name)).setText(bundle.getString("name"));
                    ((TextView)findViewById(R.id.show2_num)).setText(bundle.getString("num"));
                    ((TextView)findViewById(R.id.show2_shape)).setText(bundle.getString("size"));
                    ((TextView)findViewById(R.id.show2_urgent)).setText(bundle.getString("urgent"));
                    ((TextView)findViewById(R.id.show2_weight)).setText(bundle.getString("weight"));
                    ((TextView)findViewById(R.id.show2_ddl)).setText(bundle.getString("ddl"));
                    ((TextView)findViewById(R.id.show2_code)).setText(bundle.getString("packageno"));
                    ((TextView)findViewById(R.id.show2_place)).setText(bundle.getString("address"));
                    ((TextView)findViewById(R.id.show2_money)).setText(bundle.getString("price"));
                    ((TextView)findViewById(R.id.show2_helper_tel)).setText(bundle.getString("tel"));

                }
                super.handleMessage(msg);


            }

        };

        Thread t=new Thread(this);
        t.start();


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


        String sql="SELECT helperTel,packageNo,companyName,code,size,weight,name,deadline,urgent,price,address,state from package where packageNo="+packageno;
        try {
            boolean closed=con.isClosed();
            if((con!=null)&&(!closed)){

                ps= (PreparedStatement) con.prepareStatement(sql);
                if(ps!=null){
                    rs= ps.executeQuery();
                    if(rs!=null){
                        while(rs.next()){
                            Map<String, Object> map = new HashMap<String, Object>();
                            com=rs.getString("companyName");
                            num=rs.getString("code");
                            packageno=rs.getString("packageNo");
                            ddl=rs.getString("deadline");
                            size=rs.getString("size");
                            weight=rs.getString("weight");
                            name=rs.getString("name");
                            urgent=rs.getString("urgent");
                            price=rs.getString("price");
                            address=rs.getString("address");
                            tel=rs.getString("helperTel");
                            state=rs.getString("state");
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
        bundle.putString("com",com);
        bundle.putString("num",num);
        bundle.putString("packageno",packageno);
        bundle.putString("ddl",ddl);
        bundle.putString("size",size);
        bundle.putString("weight",weight);
        bundle.putString("name",name);
        bundle.putString("urgent",urgent);
        bundle.putString("price",price);
        bundle.putString("address",address);
        bundle.putString("state",state);
        bundle.putString("tel",tel);
        msg.setData(bundle);
        handler.sendMessage(msg);
    }
}
