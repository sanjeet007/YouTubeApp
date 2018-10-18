package com.apkglobal.androidvideotutorial.Activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.apkglobal.androidvideotutorial.Models.Configuration;
import com.apkglobal.androidvideotutorial.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class VideoPlayActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    YouTubePlayerView playerView;
    TextView description;
    TextView title;
    String video_id;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);

        playerView=findViewById(R.id.youTubePlayer);
        description=findViewById(R.id.tvDescription);
        title=findViewById(R.id.videoTitle_id);

        String title_tv         =getIntent().getExtras().getString("title");
        String description_tv   =getIntent().getExtras().getString("description");
                video_id        =getIntent().getExtras().getString("videoId");

        title.setText(title_tv);
        description.setText(description_tv);


        playerView.initialize(Configuration.KEY,this);


    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                        YouTubePlayer youTubePlayer, boolean b)
    {

        if(!b)
        {
            youTubePlayer.loadVideo(video_id);
            getPlayer();
        }

    }

    private YouTubePlayerView getPlayer()
    {
            return (YouTubePlayerView) findViewById(R.id.youTubePlayer);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult youTubeInitializationResult)
    {

        Toast.makeText(this, "Error Occurred ", Toast.LENGTH_SHORT).show();

    }
}
