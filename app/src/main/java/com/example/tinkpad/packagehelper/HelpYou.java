package com.example.tinkpad.packagehelper;

import android.content.Intent;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mysql.jdbc.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HelpYou extends AppCompatActivity{
    Button btn;
    String id,tel,packageno;
    int result=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_you);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("我是你的帮帮单助手");
        packageno=getIntent().getStringExtra("packageno");

        btn=(Button)findViewById(R.id.btn_save);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tel = ((EditText) findViewById(R.id.et_help_phone)).getText().toString();
                id = ((EditText) findViewById(R.id.et_help_id)).getText().toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        PreparedStatement ps=null;
                        com.mysql.jdbc.Connection con=null;
                        ResultSet rs=null;
                        //获取链接数据库对象
                        con= DBConnection.getConnection();
                        //MySQL 语句
                        String sql="update package set helperId=?,helperTel=?,state=? where packageNo=?";
                        try {
                            boolean closed=con.isClosed();
                            if((con!=null)&&(!closed)){

                                ps= (PreparedStatement) con.prepareStatement(sql);

                                ps.setString(1,id);//第一个参数 name 规则同上
                                ps.setString(2,tel);//第二个参数 phone 规则同上
                                ps.setString(3,"已接单");
                                ps.setString(4,packageno);

                                result=ps.executeUpdate();//返回1 执行成功

                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        DBConnection.closeAll(con,ps);//关闭相关操作
                        if(result==1){
                            Looper.prepare();
                            Toast.makeText(HelpYou.this, "提交成功！谢谢你的帮助！", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }else{
                            Looper.prepare();
                            Toast.makeText(HelpYou.this, "提交失败！请稍后重试！", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    }
                }).start();

                finish();


                }






        });


    }


}
