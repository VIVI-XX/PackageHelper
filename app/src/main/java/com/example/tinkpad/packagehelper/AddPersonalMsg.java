package com.example.tinkpad.packagehelper;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.mysql.jdbc.PreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AddPersonalMsg extends AppCompatActivity {
    Button save;
    String username, tel, dom, id;
    String TAG = "Addmsg";
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_personal_msg);
        Log.i(TAG, "onCreate:username "+username);

        save=(Button)findViewById(R.id.bt_msg_submit);
        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                username = ((EditText) findViewById(R.id.et_msg_username)).getText().toString();
                tel = ((EditText) findViewById(R.id.et_msg_tel)).getText().toString();
                dom = ((EditText) findViewById(R.id.et_msg_dom)).getText().toString();
                id = ((EditText) findViewById(R.id.et_msg_id)).getText().toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int result=-1;
                        PreparedStatement ps=null;
                        com.mysql.jdbc.Connection con=null;
                        ResultSet rs=null;
                            //获取链接数据库对象
                        con= DBConnection.getConnection();
                            //MySQL 语句
                        String sql="INSERT INTO user (studentId,userName,phoneNo,dormitory) VALUES (?,?,?,?)";
                        try {
                            boolean closed=con.isClosed();
                            if((con!=null)&&(!closed)){

                                ps= (PreparedStatement) con.prepareStatement(sql);

                                ps.setString(1,id);//第一个参数 name 规则同上
                                ps.setString(2,username);//第二个参数 phone 规则同上
                                ps.setString(3,tel);//第三个参数 content 规则同上
                                ps.setString(4,dom);//第四个参数 state 规则同上
                                result=ps.executeUpdate();//返回1 执行成功
                                Intent intent = new Intent(AddPersonalMsg.this, Frame.class);
                                intent.putExtra("id",1);
                                intent.putExtra("username",username);
                                intent.putExtra("tel",tel);
                                intent.putExtra("sid",id);


                                startActivity(intent);

                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            DBConnection.closeAll(con,ps);//关闭相关操作
                    }
                }).start();


            }


        });
    }


}
