package com.qianfeng.zhufengfm.app.fragments.discover;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.qianfeng.zhufengfm.app.R;
import com.qianfeng.zhufengfm.app.Test1Activity;

/**
 * Created with IntelliJ IDEA.
 * User: vhly[FR]
 * Date: 15/7/29
 * Email: vhly@163.com
 */
public class DiscoverRecommendFragment extends Fragment implements View.OnClickListener {

    public DiscoverRecommendFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ret = inflater.inflate(R.layout.fragment_discover_recommend, container, false);

        View btn = ret.findViewById(R.id.btnTest);

        if (btn != null) {

            btn.setOnClickListener(this);

        }

        return ret;
    }

    @Override
    public void onClick(View v) {
        FragmentActivity context = getActivity();

        Intent intent = new Intent(context, Test1Activity.class);

        context.startActivity(intent);
        // 对于startActivity 而言，新的是进入
        // 动画指定 id 为 0代表没有动画
        context.overridePendingTransition(R.anim.anim_slide_to_left, 0);

    }
}
