package com.apkglobal.androidvideotutorial.Activites;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.apkglobal.androidvideotutorial.R;

public class ErrorActivity extends AppCompatActivity
{
    TextView retry;
    private ConnectivityManager connectivityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);
        getSupportActionBar().hide();

        retry=findViewById(R.id.retry);

        retry.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                connectivityManager= (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
                if(networkInfo!=null && networkInfo.isConnected())
                {
                    Intent intent=new Intent(ErrorActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();

                }
                else
                {
                    Toast.makeText(ErrorActivity.this, "Check your Internet connection", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


}
