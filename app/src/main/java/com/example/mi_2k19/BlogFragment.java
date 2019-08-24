package com.example.mi_2k19;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class BlogFragment extends Fragment {
    private final Context mContext = getContext();
    int IMAGEUPLOAD = 100;
    View view;
    EditText blog_topic;
    EditText blog;
    Button upload_image;
    ImageView selected_image;
    Button submit;
    Uri image_uri = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_blog, null);
        blog_topic = view.findViewById(R.id.blogger_topic);
        blog = view.findViewById(R.id.blogger_blog);
        upload_image = view.findViewById(R.id.upload_img);
        selected_image = view.findViewById(R.id.selectedImage);
        submit = view.findViewById(R.id.submitButton);

        upload_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 100);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(), image_uri.toString(), Toast.LENGTH_LONG).show();
                if(image_uri != null) {
                    ((MainActivity) getActivity()).uploadFile(view, image_uri);
                }
            }
        });

        //just change the fragment_dashboard
        //with the fragment you want to inflate
        //like if the class is HomeFragment it should have R.layout.home_fragment
        //if it is DashboardFragment it should have R.layout.fragment_dashboard
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 100) {
            image_uri = data.getData();
            if (image_uri != null) {
                selected_image.setImageURI(image_uri);
            }
        }
    }
}
