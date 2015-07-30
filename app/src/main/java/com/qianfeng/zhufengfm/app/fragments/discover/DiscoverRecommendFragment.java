package com.qianfeng.zhufengfm.app.fragments.discover;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.qianfeng.zhufengfm.app.R;
import com.qianfeng.zhufengfm.app.SettingsActivity;
import com.qianfeng.zhufengfm.app.Test1Activity;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: vhly[FR]
 * Date: 15/7/29
 * Email: vhly@163.com
 */
public class DiscoverRecommendFragment extends Fragment implements AdapterView.OnItemClickListener {

    public DiscoverRecommendFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ret = inflater.inflate(R.layout.fragment_discover_recommend, container, false);

        ListView listView = (ListView) ret.findViewById(R.id.discover_recommend_list);

        if (listView != null) {

            // TODO 设置实际数据的 Adapter

            /////

            // 添加头部

            FragmentActivity activity = getActivity();

            ImageView imageView = new ImageView(activity);

            imageView.setImageResource(R.mipmap.ic_launcher);

            // 通过这个方法，添加跟随滚动的Header
            listView.addHeaderView(imageView);

            imageView = new ImageView(activity);
            imageView.setImageResource(R.mipmap.ic_action_search);
            listView.addHeaderView(imageView);

            //////////////////

            // 添加底部视图

            TextView btn = new TextView(activity);
            btn.setText("点击加载更多");

            listView.addFooterView(btn);



            ArrayList<String> strings = new ArrayList<String>();

            for (int i = 0; i < 30; i++) {
                strings.add("Java - " + i);
            }

            ArrayAdapter<String> adapter =
                    new ArrayAdapter<String>(activity, android.R.layout.simple_list_item_1, strings);

            listView.setAdapter(adapter);

            ////////////////

            listView.setOnItemClickListener(this);

        }

        return ret;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        FragmentActivity activity = getActivity();

        if (parent instanceof ListView){
            ListView listView = (ListView) parent;
            int headerViewsCount = listView.getHeaderViewsCount();

            // 调整因为 HeaderView导致的偏移
            position -= headerViewsCount;

            int footerViewsCount = listView.getFooterViewsCount();

            if(footerViewsCount > 0){
                // 数据的个数
                if(position >= 30){
                    // 点的不是数据，因为
                }else{

                }
            }else{
                // 点到数据上了
            }




        }

        Toast.makeText(activity, "点击 " + position, Toast.LENGTH_LONG).show();
    }
}
