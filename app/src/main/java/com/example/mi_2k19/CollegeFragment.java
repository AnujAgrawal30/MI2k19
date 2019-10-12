package com.example.mi_2k19;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mi_2k19.retrofit.Api;
import com.example.mi_2k19.retrofit.College;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CollegeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, null);
    }

    private static CollegeAdapter adapter;
    ArrayList<College> College;
    ListView simpleList;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        simpleList = (ListView) getView().findViewById(R.id.simpleListView);
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.homelayout, R.id.textView, countryList);
//        simpleList.setAdapter(arrayAdapter);
        College = new ArrayList<>();
        com.example.mi_2k19.retrofit.College college = new College(0, "Go Back", "No", 0);
        College.add(college);
        getCollege();
    }

    private void getCollege() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<College>> call = api.getCollege(MainActivity.city_id);

        call.enqueue(new Callback<List<College>>() {
            @Override
            public void onResponse(Call<List<College>> call, Response<List<College>> response) {
                List<College> CollegeList = response.body();
                if (getActivity()!=null) {
                    College.addAll(CollegeList);
                    Toast.makeText(getContext(), String.valueOf(College.size()), Toast.LENGTH_LONG).show();
                    adapter = new CollegeAdapter(College, getActivity(), getFragmentManager());
//                    Toast.makeText(getContext(), adapter.toString(), Toast.LENGTH_LONG).show();

                }
                else{
                    return;
                }
                simpleList.setAdapter(adapter);
//                Toast.makeText(getActivity(), Integer.toString(Users.size()), Toast.LENGTH_SHORT).show();

                //Creating an String array for the ListView
                int[] colleges = new int[CollegeList.size()];

                //looping through all the heroes and inserting the names inside the string array
                for (int i = 0; i < CollegeList.size(); i++) {
                    colleges[i] = CollegeList.get(i).getId();
//                    Toast.makeText(getActivity(),heroes[i].toString() , Toast.LENGTH_SHORT).show();
                }


                //displaying the string array into listview
//                listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, heroes));

            }

            @Override
            public void onFailure(Call<List<College>> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}