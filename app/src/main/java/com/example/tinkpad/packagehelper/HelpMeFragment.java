package com.example.tinkpad.packagehelper;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class HelpMeFragment extends Fragment {
    ImageView add;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_help_me_fragment,container);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView tv = (TextView) getView().findViewById(R.id.text_helpme_title);
        tv.setText("       Help Me");
        add = (ImageView) getActivity().findViewById(R.id.image_helpme);
        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent submit = new Intent(getActivity(),SubmitActivity.class);
                startActivity(submit);

            }


        });


    }

}
