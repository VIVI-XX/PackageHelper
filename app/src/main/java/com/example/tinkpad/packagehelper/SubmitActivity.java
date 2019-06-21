package com.example.tinkpad.packagehelper;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Looper;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.mysql.jdbc.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class SubmitActivity extends AppCompatActivity implements View.OnClickListener,DatePickerDialog.OnDateSetListener {
    String name, num, com, shape, weight, place, urgent, money,id;
    String ddl;
    String state = "未接单";
    Button sub;
    TextView date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        date=((TextView) findViewById(R.id.sub_ddl));
        date.setOnClickListener(this);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        sub=(Button)findViewById(R.id.sub_sub);
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
                urgent=((TextView) findViewById(R.id.sub_urgent)).getText().toString();
                id=date.getText().toString();


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
                        String sql1="INSERT INTO package (studentId,companyName,code,size,weight,name,deadline,urgent,price,state,address) VALUES (?,?,?,?,?,?,?,?,?,?,?)";

                        try {
                            boolean closed=con.isClosed();
                            if((con!=null)&&(!closed)){

                                ps= (PreparedStatement) con.prepareStatement(sql1);

                                ps.setString(1,id);//第一个参数 name 规则同上
                                ps.setString(2,com);//第二个参数 phone 规则同上
                                ps.setString(3,num);//第三个参数 content 规则同上
                                ps.setString(4,shape);//第四个参数 state 规则同上
                                ps.setString(5,weight);
                                ps.setString(6,name);
                                ps.setString(7,ddl);//第一个参数 name 规则同上
                                ps.setString(8,urgent);//第二个参数 phone 规则同上
                                ps.setString(9,money);//第三个参数 content 规则同上
                                ps.setString(10,state);//第四个参数 state 规则同上
                                ps.setString(11,place);
                                result=ps.executeUpdate();//返回1 执行成功
                                Intent intent = new Intent(SubmitActivity.this, Frame.class);
                                intent.putExtra("id",2);


                                startActivity(intent);

                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        DBConnection.closeAll(con,ps);//关闭相关操作
                        if(result==1){
                            Looper.prepare();
                            Toast.makeText(SubmitActivity.this, "提交成功！你的帮帮单助手正在赶来的路上！！", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }else{
                            Looper.prepare();
                            Toast.makeText(SubmitActivity.this, "提交失败！请稍后重试！", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    }
                }).start();



            }


        });


    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String desc=String.format("%d-%d-%d",year,month+1,dayOfMonth);
        date.setText(desc);
    }



    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.sub_ddl){
            Calendar calendar=Calendar.getInstance();
            DatePickerDialog dialog = new DatePickerDialog(this,R.style.MyDatePickerDialogTheme,this,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH));
            dialog.show();
        }

    }



}
