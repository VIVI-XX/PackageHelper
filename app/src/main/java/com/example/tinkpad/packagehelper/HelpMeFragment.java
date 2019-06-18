package com.example.tinkpad.packagehelper;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HelpMeFragment extends Fragment implements AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener {
    ImageView add;
    private ListView lv;
    private SimpleAdapter adapter;
    private List<Map<String, Object>> list;
    public final String TAG="HELP";
    String username,tel,sid;


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


        adapter = new SimpleAdapter(getActivity(), getData(), R.layout.list_item1,
                new String[]{"title", "com", "date","situation","code"},
                new int[]{R.id.list1_title, R.id.list1_com, R.id.list1_date,R.id.list1_sit,R.id.list1_code});      //配置适配器，并获取对应Item中的ID
        lv.setAdapter(adapter);
        lv.setOnItemClickListener((AdapterView.OnItemClickListener) this);
    }
    //数据的获取@！
    private List<? extends Map<String, ?>> getData() {
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


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        HashMap<String, String> map = (HashMap<String, String>) lv.getItemAtPosition(position);
        String title = map.get("title");
        String code = map.get("code");
        String com = map.get("com");
        String date = map.get("date");
        String situation = map.get("situation");



        TextView list1_title = view.findViewById(R.id.list1_title);
        TextView list1_code = view.findViewById(R.id.list1_code);
        TextView list1_com = view.findViewById(R.id.list1_com);
        TextView list1_date = view.findViewById(R.id.list1_date);
        TextView list1_sit = view.findViewById(R.id.list1_sit);




        String title1 = String.valueOf(list1_title.getText());
        String code1 = String.valueOf(list1_code.getText());
        String com1 = String.valueOf(list1_com.getText());
        String date1 = String.valueOf(list1_date.getText());
        String sit1 = String.valueOf(list1_sit.getText());


        //打开新的页面，传入参数
        Intent show = new Intent(this.getActivity(), ShowListActivity2.class);
        show.putExtra("title", title1);
        show.putExtra("code", code1);
        show.putExtra("com", com1);
        show.putExtra("date", date1);
        show.putExtra("situation", sit1);

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


    /*@Override
    public void onStart() {
        super.onStart();
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
                Bundle bundle=new Bundle();
                bundle.putString("studentid",sid);
                submit.putExtras(bundle);
                startActivity(submit);

            }


        });
    }*/
}


