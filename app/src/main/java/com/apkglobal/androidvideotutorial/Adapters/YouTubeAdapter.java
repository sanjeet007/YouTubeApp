package com.apkglobal.androidvideotutorial.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apkglobal.androidvideotutorial.Activites.VideoPlayActivity;
import com.apkglobal.androidvideotutorial.Models.YouTubeModel;
import com.apkglobal.androidvideotutorial.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class YouTubeAdapter extends RecyclerView.Adapter<YouTubeAdapter.YouTubeHolder> {

    private Context context;
    private List<YouTubeModel> list;
    RequestOptions options;

    public YouTubeAdapter(Context context, List<YouTubeModel> list)
    {
        this.context = context;
        this.list = list;

        options=new RequestOptions().centerCrop().placeholder
                (R.drawable.loading_shape).error(R.drawable.loading_shape);
    }

    @NonNull
    @Override
    public YouTubeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        final YouTubeHolder holder=new YouTubeHolder(v);

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(context, VideoPlayActivity.class);
                intent.putExtra("title",list.get(holder.getAdapterPosition()).getTitle());
                intent.putExtra("description",list.get(holder.getAdapterPosition()).getDescription());
                intent.putExtra("videoId",list.get(holder.getAdapterPosition()).getVideoId());
                context.startActivity(intent);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull YouTubeHolder holder, int position)
    {

        holder.videoTitle.setText(list.get(position).getTitle());
        holder.channelName.setText(list.get(position).getChannelTitle());
        Glide.with(context).load(list.get(position).getUrl()).apply(options).into(holder.thumbnail);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class YouTubeHolder extends RecyclerView.ViewHolder {

        private ImageView thumbnail;
        private TextView  videoTitle;
        private TextView  channelName;
        LinearLayout item;

        public YouTubeHolder(View itemView)
        {
            super(itemView);

            thumbnail=itemView.findViewById(R.id.image_id);
            videoTitle=itemView.findViewById(R.id.title_id);
            channelName=itemView.findViewById(R.id.channel_name_id);
            item=itemView.findViewById(R.id.layout_item_id);


        }
    }
}
