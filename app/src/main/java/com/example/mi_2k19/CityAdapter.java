package com.example.mi_2k19;


import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mi_2k19.retrofit.Cities;

import java.util.ArrayList;

public class CityAdapter extends ArrayAdapter<Cities> implements View.OnClickListener{

    private ArrayList<Cities> dataSet;
    Context mContext;
    private FragmentManager fragmentManager;

    // View lookup cache
    private static class ViewHolder {
        TextView id;
        TextView city_name;
    }

    public CityAdapter(ArrayList<Cities> data, Context context, FragmentManager fragmentManager) {
        super(context, R.layout.citylayout, data);
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
        Cities city = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        final ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.citylayout, parent, false);

            viewHolder.id = convertView.findViewById(R.id.id);
            viewHolder.city_name = convertView.findViewById(R.id.city_name);

            result = convertView;

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

//        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
//        result.startAnimation(animation);
//        lastPosition = position;


        viewHolder.city_name.setText((city.getCity_name()));
        viewHolder.id.setText(String.valueOf(city.getId()));

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.city_id = viewHolder.id.getText().toString();
                Fragment fragment = new CollegeFragment();
                loadFragment(fragment);
            }
        });

        // Return the completed view to render on screen
        return convertView;
    }
}

