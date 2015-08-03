package com.wgq.zhufengfm.app.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 15-8-2.
 */
public class DetailList {
    private int trackId;
    private int uid;
    private String playUrl64;
    private String playUrl32;
    private String downloadUrl;
    private String playPathAacv164;
    private String playPathAacv224;
    private String title;
    private double duration;
    public void parseDetailst(JSONObject json){
        try {
            trackId = json.getInt("trackId");
            uid = json.getInt("uid");
            playUrl64 = json.getString("playUrl64");
            playUrl32 = json.getString("playUrl32");
            downloadUrl = json.getString("downloadUrl");
            playPathAacv164 = json.getString("playPathAacv164");
            playPathAacv224 = json.getString("playPathAacv224");
            title = json.getString("title");
            duration = json.getDouble("duration");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public int getTrackId() {
        return trackId;
    }

    public int getUid() {
        return uid;
    }

    public String getPlayUrl64() {
        return playUrl64;
    }

    public String getPlayUrl32() {
        return playUrl32;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public String getPlayPathAacv164() {
        return playPathAacv164;
    }

    public String getPlayPathAacv224() {
        return playPathAacv224;
    }

    public String getTitle() {
        return title;
    }

    public double getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "DetailList{" +
                "trackId=" + trackId +
                ", uid=" + uid +
                ", playUrl64='" + playUrl64 + '\'' +
                ", playUrl32='" + playUrl32 + '\'' +
                ", downloadUrl='" + downloadUrl + '\'' +
                ", playPathAacv164='" + playPathAacv164 + '\'' +
                ", playPathAacv224='" + playPathAacv224 + '\'' +
                ", title='" + title + '\'' +
                ", duration=" + duration +
                '}';
    }
}
