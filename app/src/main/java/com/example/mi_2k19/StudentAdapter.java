package com.example.mi_2k19;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mi_2k19.retrofit.Api;
import com.example.mi_2k19.retrofit.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StudentAdapter extends ArrayAdapter<Student> implements View.OnClickListener{

    private ArrayList<Student> dataSet;
    Context mContext;
    private FragmentManager fragmentManager;
    private Call<List<Student>> call;

    // View lookup cache
    private static class ViewHolder {
        TextView college;
        TextView name;
        TextView type1;
        TextView fb_id;
        TextView insta_id;
        TextView sop;
    }

    public StudentAdapter(ArrayList<Student> data, Context context, FragmentManager fragmentManager, Call<List<Student>> call) {
        super(context, R.layout.studentlayout, data);
        this.dataSet = data;
        this.mContext=context;
        this.fragmentManager = fragmentManager;
        this.call = call;
    }

    @Override
    public void onClick(View v) {
    }


    public boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Student student = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        final ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.studentlayout, parent, false);

            viewHolder.college = convertView.findViewById(R.id.college);
            viewHolder.name = convertView.findViewById(R.id.name);
            viewHolder.type1 = convertView.findViewById(R.id.type1);
            viewHolder.fb_id = convertView.findViewById(R.id.fb_id);
            viewHolder.insta_id = convertView.findViewById(R.id.insta_id);
            viewHolder.sop = convertView.findViewById(R.id.sop);

            result = convertView;

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

//        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
//        result.startAnimation(animation);
//        lastPosition = position;


        viewHolder.name.setText((student.getName()));
        viewHolder.college.setText(String.valueOf(student.getCollege()));
        viewHolder.type1.setText(String.valueOf(student.getType1()));
        viewHolder.fb_id.setText(String.valueOf(student.getFb_id()));
        viewHolder.insta_id.setText(String.valueOf(student.getInsta_id()));
        viewHolder.sop.setText(String.valueOf(student.getSop()));

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewHolder.name.getText().toString().equals("Go Back")){
                    Fragment fragment = new CollegeFragment();
                    loadFragment(fragment);
                }
            }
        });

        // Return the completed view to render on screen
        return convertView;
    }
}

