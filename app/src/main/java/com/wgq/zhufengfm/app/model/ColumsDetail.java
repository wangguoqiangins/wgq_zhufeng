package com.wgq.zhufengfm.app.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 15-8-2.
 */
public class ColumsDetail {
    private int ret;
    private List<DetailList> data;
    private String albumTitle;
    private int msg;
    public void parseColumdetail(JSONObject json){
        data = new LinkedList<DetailList>();
        try {
            ret = json.getInt("ret");
            JSONArray jsonArray = json.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonsub = jsonArray.getJSONObject(i);//获取每一个DetailList对象的JSONObject
                DetailList detailList = new DetailList();//new出来需要赋值的DetailList对象
                detailList.parseDetailst(jsonsub);//解析每一个对象
                data.add(detailList);//把解析出来的每一个对象的值添加到data中
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getRet() {
        return ret;
    }

    public List<DetailList> getData() {
        return data;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public int getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "ColumsDetail{" +
                "ret=" + ret +
                ", data=" + data +
                ", albumTitle='" + albumTitle + '\'' +
                ", msg=" + msg +
                '}';
    }
}
