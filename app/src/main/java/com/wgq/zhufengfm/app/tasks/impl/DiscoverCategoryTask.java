package com.wgq.zhufengfm.app.tasks.impl;

import com.wgq.zhufengfm.app.Constants;
import com.wgq.zhufengfm.app.client.ClientDiscoverAPI;
import com.wgq.zhufengfm.app.tasks.BaseTask;
import com.wgq.zhufengfm.app.tasks.TaskCallback;
import com.wgq.zhufengfm.app.tasks.TaskResult;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created with IntelliJ IDEA.
 * User: vhly[FR]
 * Date: 15/7/29
 * Email: vhly@163.com
 */

/**
 * 发现部分分类获取任务
 */
public class DiscoverCategoryTask extends BaseTask {

    public DiscoverCategoryTask(TaskCallback callback) {
        super(callback);
    }

    @Override
    protected TaskResult doInBackground(String... params) {
        TaskResult ret = new TaskResult();

        ret.taskId = Constants.TASK_DISCOVER_CATEGORIES;

        // 调API
        String str = ClientDiscoverAPI.getDiscoverCategories();

        try {
            ret.data = new JSONObject(str);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return ret;
    }
}
