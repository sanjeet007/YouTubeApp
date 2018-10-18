package com.apkglobal.androidvideotutorial.Activites;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.apkglobal.androidvideotutorial.Adapters.YouTubeAdapter;
import com.apkglobal.androidvideotutorial.Models.YouTubeModel;
import com.apkglobal.androidvideotutorial.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<YouTubeModel> mList;
    private String URL="ENTER YOUR URL HERE";
    RecyclerView recyclerView;
    private ConnectivityManager connectivityManager;

    @Override
    protected void onStart() {
        super.onStart();

        connectivityManager= (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        if(networkInfo!=null && networkInfo.isConnected())
        {
            Toast.makeText(this, "Internet Connected", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent intent=new Intent(this,ErrorActivity.class);
            startActivity(intent);
            finish();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("YouTube");

        recyclerView=findViewById(R.id.recyclerView);
        mList=new ArrayList<>();

        fetchData();
    }


    private void fetchData()
    {
        StringRequest stringRequest=new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {

                try
                {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("items");
                    for (int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject1=jsonArray.getJSONObject(i);
                        YouTubeModel model=new YouTubeModel();

                        JSONObject objectSnippet=jsonObject1.getJSONObject("snippet");
                        model.setTitle(objectSnippet.getString("title"));
                        model.setChannelTitle(objectSnippet.getString("channelTitle"));
                        model.setDescription(objectSnippet.getString("description"));

                        JSONObject objThumbnail=objectSnippet.getJSONObject("thumbnails").getJSONObject("high");
                        model.setUrl(objThumbnail.getString("url"));

                        JSONObject objVideoId=objectSnippet.getJSONObject("resourceId");
                        model.setVideoId(objVideoId.getString("videoId"));
                        mList.add(model);
                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }

                setUpRecyclerView(mList);

            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {

            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void setUpRecyclerView(List<YouTubeModel> mList)
    {
        YouTubeAdapter adapter=new YouTubeAdapter(this,mList);
        adapter.notifyDataSetChanged();
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
