package com.example.tinkpad.packagehelper;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HelpYouFragment extends Fragment {
    ImageView mine;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_help_you_fragment,container);
    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView tv =(TextView)getView().findViewById(R.id.text_helpyou_title);
        tv.setText("       Help You");
        mine=getActivity().findViewById(R.id.image_helpyou);
        mine.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent show = new Intent(getActivity(),ShowListActivity.class);
                startActivity(show);

            }


        });


    }


}
