package com.example.tinkpad.packagehelper;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowListActivity2 extends AppCompatActivity {
    Button btn_home;
    String title="";
    String code="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list2);
        title = getIntent().getStringExtra("title");
        code = getIntent().getStringExtra("code");
        ((TextView) findViewById(R.id.show2_title)).setText(title);
        ((TextView) findViewById(R.id.show2_code)).setText(code);


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


    }
}
