package com.example.mi_2k19;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mi_2k19.retrofit.Api;
import com.example.mi_2k19.retrofit.College;
import com.example.mi_2k19.retrofit.College;
import com.example.mi_2k19.retrofit.Hero;
import com.example.mi_2k19.retrofit.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StudentFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, null);
    }

    private static StudentAdapter adapter;
    ArrayList<Student> Student;
    ListView simpleList;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        simpleList = (ListView) getView().findViewById(R.id.simpleListView);
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.homelayout, R.id.textView, countryList);
//        simpleList.setAdapter(arrayAdapter);
        Student = new ArrayList<>();
        com.example.mi_2k19.retrofit.Student student = new Student("Go Back", "CHN", "Go Back", "Go Back", "Go Back", "Go Back");
        Student.add(student);
        getStudent();
    }

    private void getStudent() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<Student>> call = api.getStudent(MainActivity.college_id);

        call.enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                List<Student> StudentList = response.body();
                if (getActivity() !=null) {
                    Student.addAll(StudentList);
                    Toast.makeText(getContext(), String.valueOf(Student.size()), Toast.LENGTH_LONG).show();
                    adapter = new StudentAdapter(Student, getActivity(), getFragmentManager(), call);
//                    Toast.makeText(getContext(), adapter.toString(), Toast.LENGTH_LONG).show();

                }
                else{
                    return;
                }
                simpleList.setAdapter(adapter);
//                Toast.makeText(getActivity(), Integer.toString(Users.size()), Toast.LENGTH_SHORT).show();

                //Creating an String array for the ListView
//                int[] students = new int[StudentList.size()];

                //looping through all the heroes and inserting the names inside the string array
//                for (int i = 0; i < StudentList.size(); i++) {
//                    students[i] = StudentList.get(i).getId();
////                    Toast.makeText(getActivity(),heroes[i].toString() , Toast.LENGTH_SHORT).show();
//                }


                //displaying the string array into listview
//                listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, heroes));

            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}