package com.apkglobal.androidvideotutorial.Models;

public class YouTubeModel
{
    private String videoId;
    private String title;
    private String description;
    private String url;
    private String channelTitle;
    private int position;

    public YouTubeModel() {
    }

    public YouTubeModel(String videoId, String title, String description,
                        String url, String channelTitle, int position)
    {
        this.videoId        = videoId;
        this.title          = title;
        this.description    = description;
        this.url            = url;
        this.channelTitle   = channelTitle;
        this.position       = position;
    }

    public String getVideoId() {
        return videoId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public int getPosition() {
        return position;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
