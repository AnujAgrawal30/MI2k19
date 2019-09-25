package com.example.mi_2k19;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mi_2k19.retrofit.Api;
import com.example.mi_2k19.retrofit.Cities;
import com.example.mi_2k19.retrofit.College;
import com.example.mi_2k19.retrofit.Student;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//implement the interface OnNavigationItemSelectedListener in your activity class
public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    static public String college_id;
    static public String city_id;
    private final Context mContext = this;
    private List<String> city_ids;
    Map<String, String[]> cities = new HashMap<>();
    EditText blog_topic;
    EditText blog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.homelayout, R.id.textView, countryList);
//        simpleList.setAdapter(arrayAdapter);

        //loading the default fragment
        loadFragment(new HomeFragment());

        //getting bottom navigation view and attaching the listener
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.navigation_home:
                fragment = new HomeFragment();
                break;

            case R.id.navigation_dashboard:
                fragment = new BlogFragment();
                break;

            case R.id.navigation_notifications:
                fragment = new MenuFragment();
                break;

        }

        return loadFragment(fragment);
    }

    public boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }


    static public void getNumbers(int id){
        List<String> college_ids;

    }

    //Upload File
    public void uploadFile(View view, Uri fileUri) {
        blog = view.findViewById(R.id.blogger_blog);
        blog_topic = view.findViewById(R.id.blogger_topic);

        String filePath = getRealPathFromUri(fileUri);
        if (filePath != null && !filePath.isEmpty()) {
            File file = new File(filePath);
            if (file.exists()) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Api.BASE_URL)
                        .build();

                Api service = retrofit.create(Api.class);

                // creates RequestBody instance from file
                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                // MultipartBody.Part is used to send also the actual filename
//                MultipartBody.Part body1 = MultipartBody.Part.createFormData("pic_url", file.getName(), requestFile);
                MultipartBody.Part body2 = MultipartBody.Part.createFormData("bloger_pic", file.getName(), requestFile);
                // adds another part within the multipart request

//                RequestBody id = RequestBody.create(MediaType.parse("text/plain"), "1");
                RequestBody bloger_name = RequestBody.create(MediaType.parse("text/plain"), "Anuj");
                RequestBody pic_url = RequestBody.create(MediaType.parse("text/plain"), "Url url url");
                RequestBody types = RequestBody.create(MediaType.parse("text/plain"), "CH");;
                RequestBody college = RequestBody.create(MediaType.parse("text/plain"), "IITB");;
                RequestBody bloger_topic = RequestBody.create(MediaType.parse("text/plain"), blog_topic.getText().toString());
                RequestBody bloger_blog = RequestBody.create(MediaType.parse("text/plain"), blog.getText().toString());
                RequestBody bloger_status = RequestBody.create(MediaType.parse("text/plain"),"no");
                MultipartBody.Part bloger_pic = body2;
                RequestBody fblink = RequestBody.create(MediaType.parse("text/plain"),"sdklfjslkfd");
                RequestBody instalink = RequestBody.create(MediaType.parse("text/plain"),"sdklfjslkfdksdjf");

                // executes the request
                Call<ResponseBody> call = service.postFile(
                        bloger_name,
                        pic_url,
                        types,
                        college,
                        bloger_topic,
                        bloger_blog,
                        bloger_status,
                        bloger_pic,
                        fblink,
                        instalink
                );
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call,
                                           Response<ResponseBody> response) {
                        Toast.makeText(getApplicationContext(), response.body().toString(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
    }


    public String getRealPathFromUri(final Uri uri) {
        // DocumentProvider
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && DocumentsContract.isDocumentUri(mContext, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(mContext, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(mContext, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {

            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(mContext, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    private String getDataColumn(Context context, Uri uri, String selection,
                                 String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    private boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    private boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    private boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    private boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

}