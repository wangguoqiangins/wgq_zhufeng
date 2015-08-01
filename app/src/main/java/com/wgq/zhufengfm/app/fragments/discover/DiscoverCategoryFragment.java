package com.wgq.zhufengfm.app.fragments.discover;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wgq.zhufengfm.app.Constants;
import com.wgq.zhufengfm.app.R;
import com.wgq.zhufengfm.app.data.DataStore;
import com.wgq.zhufengfm.app.model.discover.DiscoverCategory;
import com.wgq.zhufengfm.app.parsers.DataParser;
import com.wgq.zhufengfm.app.tasks.TaskCallback;
import com.wgq.zhufengfm.app.tasks.TaskResult;
import com.wgq.zhufengfm.app.tasks.impl.DiscoverCategoryTask;
import com.wgq.zhufengfm.app.util.MyLog;
import org.json.JSONObject;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: vhly[FR]
 * Date: 15/7/29
 * Email: vhly@163.com
 */

/**
 * 分类
 */
public class DiscoverCategoryFragment extends Fragment implements TaskCallback {

    private static final String TAG = "DCF";

    public DiscoverCategoryFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 1. 判断有没有分类
        List<DiscoverCategory> categories =
                DataStore.getInstance().getDiscoverCategories();

        if (categories != null && !categories.isEmpty()) {
            // 有分类
            MyLog.d(TAG, "Discover category has.");
        } else {
            // 无分类
            MyLog.d(TAG, "Discover category need request.");
            DiscoverCategoryTask task =
                    new DiscoverCategoryTask(this);

            task.execute();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_discover_category, container, false);
    }

    @Override
    public void onTaskFinished(TaskResult result) {
        if (result != null) {

            int taskId = result.taskId;
            Object data = result.data;

            if (taskId == Constants.TASK_DISCOVER_CATEGORIES) {
                if (data != null) {
                    if (data instanceof JSONObject) {
                        List<DiscoverCategory> categories = DataParser.parseDiscoverCategories((JSONObject) data);
                        if (categories != null) {

                            DataStore.getInstance().setDiscoverCategories(categories);

                            // TODO 利用分类，制作UI界面

                        }
                    }
                }
            }

        }
    }
}
