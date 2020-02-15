package com.nags.nasainfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.IDNA;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private List<InfoModel> infoModels = new ArrayList<>();
    ImageListAdapter mAdapter;
    private RecyclerView mGridView;
    String json = null;

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("NASAInfo");
        setSupportActionBar(toolbar);

        mGridView=findViewById(R.id.recyclerView_grid);

        loadRecyclerView();

    }

    private void loadRecyclerView() {

        try {
            loadJSONFromAsset(getApplicationContext());
            JSONArray jsonArray= new JSONArray(json);
            for (int i=0;i<jsonArray.length();i++){
                JSONObject object=jsonArray.getJSONObject(i);
                InfoModel model= new InfoModel(object.getString("date"),
                        object.getString("explanation"),object.getString("title")
                        ,object.getString("url"));

                infoModels.add(model);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        onItemClick();

    }

    private void onItemClick() {
        ImageListAdapter adapter = new ImageListAdapter(infoModels,getApplicationContext());
        mGridView.setItemAnimator(new DefaultItemAnimator());
        mGridView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mGridView.setLayoutManager(manager);

        adapter.setOnItemClickListener(new ImageListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, InfoModel infoModel) {
                InfoModel infoModel1=infoModels.get(position);

                Intent intent= new Intent(MainActivity.this,ImageDescriptionActivity.class);
                intent.putExtra("imageurl",infoModel1.getUrl());
                intent.putExtra("imageExp",infoModel1.getExplanation());
                startActivity(intent);
            }
        });
    }


    public String loadJSONFromAsset(Context context) {

        try {
            InputStream is = context.getAssets().open("data.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, StandardCharsets.UTF_8);

            is.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }



        return json;

    }

}
