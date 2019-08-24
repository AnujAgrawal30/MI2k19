package com.example.mi_2k19;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mi_2k19.retrofit.Cities;
import com.example.mi_2k19.retrofit.College;
import com.example.mi_2k19.retrofit.Hero;
import com.example.mi_2k19.retrofit.Student;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Hero> implements View.OnClickListener{

    private ArrayList<Hero> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        ImageView blogerpic;
        ImageView profilepic;
        ImageButton fbshare;
        ImageButton instashare;
        LinearLayout links;

        TextView id;
        TextView bloger_name;
        TextView pic_url;
        TextView types;
        TextView college;
        TextView bloger_topic;
        TextView bloger_blog;
        TextView bloger_status;
        TextView fblink;
        TextView instalink;
    }

    public CustomAdapter(ArrayList<Hero> data, Context context) {
        super(context, R.layout.homelayout, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Hero user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.homelayout, parent, false);

            viewHolder.fbshare =(ImageButton) convertView.findViewById(R.id.fbshare);
            viewHolder.instashare =(ImageButton) convertView.findViewById(R.id.instashare);
            viewHolder.links=(LinearLayout)convertView.findViewById(R.id.links);

            viewHolder.blogerpic = (ImageView) convertView.findViewById(R.id.bloger_pic);
            viewHolder.profilepic = (ImageView) convertView.findViewById(R.id.profileimg);


            viewHolder.bloger_name = convertView.findViewById(R.id.bloger_name);
            viewHolder.pic_url = convertView.findViewById(R.id.pic_url);
            viewHolder.types = convertView.findViewById(R.id.types);
            viewHolder.college = convertView.findViewById(R.id.college);
            viewHolder.bloger_topic = convertView.findViewById(R.id.bloger_topic);
            viewHolder.bloger_blog = convertView.findViewById(R.id.bloger_blog);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

//        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
//        result.startAnimation(animation);
//        lastPosition = position;

        
        viewHolder.bloger_name.setText(user.getBloger_name());

        if(user.getTypes().equals("MI")){
            viewHolder.links.setVisibility(View.VISIBLE);
        viewHolder.types.setText("College Connect Program");
        }
        else if(user.getTypes().equals("CH")||user.getTypes().equals("CHN")){
            viewHolder.links.setVisibility(View.GONE);
            viewHolder.types.setText("College Head");

//            links.setEnabled(false);
        }
        else if(user.getTypes().equals("CA")) {
            viewHolder.types.setText("College Associate");
            viewHolder.links.setVisibility(View.GONE);
        }
//        viewHolder.bloger_status.setText(user.getBloger_status());
        viewHolder.college.setText(user.getCollege());
        viewHolder.bloger_topic.setText(user.getBloger_topic());
        viewHolder.bloger_blog.setText(user.getBloger_blog());
        final String fblink =user.getFblink();
        final String instalink =user.getInstalink();
        viewHolder.fbshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoToURL(fblink);
            }
        });
        viewHolder.instashare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoToURL(instalink);
            }
        });

//        viewHolder.bloger_pic.setText(user.getBloger_pic());
//        viewHolder.fblink.setText(user.getFblink());
//        viewHolder.instalink.setText(user.getInstalink());
        String profileUri = user.getPic_url();
        Picasso.with(getContext()).load(profileUri).into(viewHolder.profilepic);

        String imageUri = "https://api.moodi.org"+user.getBloger_pic();
        Picasso.with(getContext()).load(imageUri).into(viewHolder.blogerpic);


        // Return the completed view to render on screen
        return convertView;
    }
    void GoToURL(String url){
        Uri uri = Uri.parse(url);
        Intent intent= new Intent(Intent.ACTION_VIEW,uri);
        mContext.startActivity(intent);

    }
}

