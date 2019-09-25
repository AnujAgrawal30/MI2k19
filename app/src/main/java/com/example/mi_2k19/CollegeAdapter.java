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

import com.example.mi_2k19.retrofit.College;

import java.util.ArrayList;

public class CollegeAdapter extends ArrayAdapter<College> implements View.OnClickListener{

    private ArrayList<College> dataSet;
    Context mContext;
    private FragmentManager fragmentManager;

    // View lookup cache
    private static class ViewHolder {
        TextView id;
        TextView college_name;
        TextView college_status;
        TextView located_city;
    }

    public CollegeAdapter(ArrayList<College> data, Context context, FragmentManager fragmentManager) {
        super(context, R.layout.collegelayout, data);
        this.dataSet = data;
        this.mContext=context;
        this.fragmentManager = fragmentManager;
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
        College college = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        final ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.collegelayout, parent, false);

            viewHolder.id = convertView.findViewById(R.id.id);
            viewHolder.college_name = convertView.findViewById(R.id.college_name);
            viewHolder.college_status = convertView.findViewById(R.id.college_status);
            viewHolder.located_city = convertView.findViewById(R.id.located_city);

            result = convertView;

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

//        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
//        result.startAnimation(animation);
//        lastPosition = position;


        viewHolder.college_name.setText((college.getCollege_name()));
        viewHolder.id.setText(String.valueOf(college.getId()));
        viewHolder.college_status.setText(String.valueOf(college.getStatus()));
        viewHolder.located_city.setText(String.valueOf(college.getLocated_city()));

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewHolder.college_name.getText().toString().equals("Go Back")){
                    Fragment fragment = new MenuFragment();
                    loadFragment(fragment);
                    return;
                }
                MainActivity.college_id = viewHolder.id.getText().toString();
                Fragment fragment = new StudentFragment();
                loadFragment(fragment);
            }
        });

        // Return the completed view to render on screen
        return convertView;
    }
}

