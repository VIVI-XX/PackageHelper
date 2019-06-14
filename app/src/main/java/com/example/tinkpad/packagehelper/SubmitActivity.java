package com.example.tinkpad.packagehelper;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.TextView;

public class SubmitActivity extends AppCompatActivity {
    public static TextView s_username,s_tel,s_name,s_num,s_com,s_shape,s_place,s_money,s_ddl;
    public static RadioButton s_urgent1,s_urgent2;
    String username,tel,name,num,com,shape,place,urgent,money;
    String ddl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }

        s_username=(TextView) findViewById(R.id.sub_username);
        s_tel=(TextView) findViewById(R.id.sub_tel);
        s_name=(TextView) findViewById(R.id.sub_name);
        s_num=(TextView) findViewById(R.id.sub_num);
        s_shape=(TextView) findViewById(R.id.sub_shape);
        s_place=(TextView) findViewById(R.id.sub_place);
        s_money=(TextView) findViewById(R.id.sub_money);
        s_ddl=(TextView) findViewById(R.id.sub_ddl);
        s_urgent1=(RadioButton) findViewById(R.id.sub_urgent1);
        s_urgent2=(RadioButton) findViewById(R.id.sub_urgent2);

        if(s_urgent1.isChecked()){
            urgent=s_urgent1.getText().toString();
        }else if(s_urgent2.isChecked()) {
            urgent=s_urgent2.getText().toString();
        }

        username=s_username.getText().toString();
        tel=s_tel.getText().toString();
        name=s_name.getText().toString();
        num=s_num.getText().toString();
        shape=s_shape.getText().toString();
        place=s_place.getText().toString();
        money=s_money.getText().toString();
        ddl=s_ddl.getText().toString();







    }
}
