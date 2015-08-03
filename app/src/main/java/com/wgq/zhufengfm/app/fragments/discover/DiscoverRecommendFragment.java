package com.wgq.zhufengfm.app.fragments.discover;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import com.wgq.zhufengfm.app.ColumsDetaileActivity;
import com.wgq.zhufengfm.app.Constants;
import com.wgq.zhufengfm.app.R;
import com.wgq.zhufengfm.app.Test1Activity;
import com.wgq.zhufengfm.app.adapters.DiscoverRecommendAdapter;
import com.wgq.zhufengfm.app.client.HttpUtil;
import com.wgq.zhufengfm.app.model.DiscoverRecommend;
import com.wgq.zhufengfm.app.parsers.DataParser;
import com.wgq.zhufengfm.app.tasks.TaskCallback;
import com.wgq.zhufengfm.app.tasks.TaskResult;
import com.wgq.zhufengfm.app.tasks.impl.DiscoverRecommendTask;
import org.json.JSONObject;

/**
 * Created with IntelliJ IDEA.
 * User: vhly[FR]
 * Date: 15/7/29
 * Email: vhly@163.com
 */
public class DiscoverRecommendFragment extends Fragment implements AdapterView.OnItemClickListener, TaskCallback, View.OnClickListener {

    private DiscoverRecommendAdapter adapter;

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
            adapter = new DiscoverRecommendAdapter(getActivity());
            adapter.setOnClickListener(this);
            listView.setAdapter(adapter);
        }

        return ret;
    }

    @Override
    public void onResume() {
        super.onResume();

        DiscoverRecommendTask task =
                new DiscoverRecommendTask(this);
        task.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        FragmentActivity activity = getActivity();

        if (parent instanceof ListView) {
            ListView listView = (ListView) parent;
            int headerViewsCount = listView.getHeaderViewsCount();

            // 调整因为 HeaderView导致的偏移
            position -= headerViewsCount;

            int footerViewsCount = listView.getFooterViewsCount();

            if (footerViewsCount > 0) {
                // 数据的个数
                if (position >= 30) {
                    // 点的不是数据，因为
                } else {

                }
            } else {
                // 点到数据上了
            }


        }

        Toast.makeText(activity, "点击 " + position, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onTaskFinished(TaskResult result) {
        if (result != null) {

            int taskId = result.taskId;

            Object data = result.data;

            if(taskId == Constants.TASK_DISCOVER_RECOMMEND){

                if (data != null) {
                    if (data instanceof JSONObject){
                        JSONObject json = (JSONObject) data;
                        DiscoverRecommend discoverRecommend = DataParser.parseDiscoverRecommend(json);

                        adapter.setRecommend(discoverRecommend);

                    }
                }

            }

        }
    }

    @Override
    public void onClick(View v) {




        int id = v.getId();
        Object tag = v.getTag();
        if (id == R.id.item_recommend_album_more) {
            String s = (String) tag;
            if (Constants.TAG_DISCOVER_RECOMMEND_EDITOR.equals(tag)) {
                Toast.makeText(getActivity(), "点了小编推荐", Toast.LENGTH_LONG).show();
            } else if (s.startsWith(Constants.TAG_DISCOVER_HOT_RECOMMEND)) {
                //TODO 热门推荐的更多
                int index = s.indexOf(':');
                s = s.substring(index + 1);
                int cid = Integer.parseInt(s);
            }
        }
            else if(v instanceof ImageView){
                //TODO 如果是图片，相当于点击了专辑,跳专辑列表
                if (tag != null) {
                    if(tag instanceof String[]){
                        String [] ss = (String[]) tag;
                        String albumId = ss[1];
                        String trackId = ss[2];
                        Log.d("=======albumId",albumId);
                        Log.d("=======trackId",trackId);
                        //TODO 打开新界面，调用接口20
                        //测试用的activity
//                        String detailpath = "http://mobile.ximalaya.com/mobile/playlist/album?device=android&albumId=392497&trackId=8060450";
//                        String detailpath = "http://mobile.ximalaya.com/mobile/playlist/album?device=android&albumId321796&trackId8055346";
                        Intent intent = new Intent(getActivity(), ColumsDetaileActivity.class);
                        String path = "http://mobile.ximalaya.com/mobile/playlist/album?device=android";
                        StringBuilder sb = new StringBuilder();
                        sb.append(path);
                        sb.append("&albumId="+Integer.parseInt(albumId));
                        sb.append("&trackId="+Integer.parseInt(trackId));
                        String stringpath = sb.toString();
                        Log.d("======stringpath",stringpath);
                        intent.putExtra("detailpath", stringpath);
                        startActivity(intent);
                    }
                }
            }


    }
}
