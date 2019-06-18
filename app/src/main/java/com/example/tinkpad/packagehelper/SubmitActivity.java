package com.example.tinkpad.packagehelper;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.mysql.jdbc.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubmitActivity extends AppCompatActivity {
    public static TextView s_name, s_num, s_com, s_shape, s_place, s_money, s_ddl, s_weight;
    public static RadioButton s_urgent1, s_urgent2;
    String name, num, com, shape, weight, place, urgent, money;
    String ddl;
    String state = "未接单";
    Button sub;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        /*sub = (Button) findViewById(R.id.bt_msg_submit);
        sub.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });



        /*sub=(Button)findViewById(R.id.bt_msg_submit);
        sub.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                name=((TextView) findViewById(R.id.sub_name)).getText().toString();
                num=((TextView) findViewById(R.id.sub_num)).getText().toString();
                com=((TextView) findViewById(R.id.sub_com)).getText().toString();
                shape=((TextView) findViewById(R.id.sub_shape)).getText().toString();
                weight=((TextView) findViewById(R.id.sub_weight)).getText().toString();
                place=((TextView) findViewById(R.id.sub_place)).getText().toString();
                money=((TextView) findViewById(R.id.sub_money)).getText().toString();
                ddl=((TextView) findViewById(R.id.sub_ddl)).getText().toString();
                if(s_urgent1.isChecked()){
                    urgent=((RadioButton) findViewById(R.id.sub_urgent1)).getText().toString();
                }else{
                    urgent=((RadioButton) findViewById(R.id.sub_urgent2)).getText().toString();
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int result1,result2=-1;
                        PreparedStatement ps=null;
                        com.mysql.jdbc.Connection con=null;
                        ResultSet rs=null;
                        //获取链接数据库对象
                        con= DBConnection.getConnection();
                        //MySQL 语句
                        String sql1="INSERT INTO package (studentId,companyName,code,size,weight,name) VALUES (?,?,?,?,?,?)";
                        String sql2="INSERT INTO order (deadline,urgent,price,state,address) VALUES (?,?,?,?,?)";

                        try {
                            boolean closed=con.isClosed();
                            if((con!=null)&&(!closed)){

                                ps= (PreparedStatement) con.prepareStatement(sql1);

                                ps.setString(1,"41723068");//第一个参数 name 规则同上
                                ps.setString(2,com);//第二个参数 phone 规则同上
                                ps.setString(3,num);//第三个参数 content 规则同上
                                ps.setString(4,shape);//第四个参数 state 规则同上
                                ps.setString(5,weight);
                                ps.setString(6,name);
                                result1=ps.executeUpdate();//返回1 执行成功

                                ps= (PreparedStatement) con.prepareStatement(sql2);

                                ps.setString(1,ddl);//第一个参数 name 规则同上
                                ps.setString(2,urgent);//第二个参数 phone 规则同上
                                ps.setString(3,money);//第三个参数 content 规则同上
                                ps.setString(4,state);//第四个参数 state 规则同上
                                ps.setString(5,place);
                                result1=ps.executeUpdate();//返回1 执行成功
                                Intent intent = new Intent(SubmitActivity.this, Frame.class);
                                intent.putExtra("id",2);


                                startActivity(intent);

                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        DBConnection.closeAll(con,ps);//关闭相关操作
                    }
                }).start();



            }


        });*/


    }
}
