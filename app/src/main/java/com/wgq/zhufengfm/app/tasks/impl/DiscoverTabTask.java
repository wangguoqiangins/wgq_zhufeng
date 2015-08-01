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
public class DiscoverTabTask extends BaseTask {

    public DiscoverTabTask(TaskCallback callback) {
        super(callback);
    }

    @Override
    protected TaskResult doInBackground(String... params) {
        TaskResult ret = new TaskResult();

        ret.taskId = Constants.TASK_DISCOVER_TABS;

        String str = ClientDiscoverAPI.getDiscoverTabs();

        if (str != null) {
            try {
                //返回JSON是为了让接收数据的接口实现，
                // 来检查数据的情况，不直接返回实体
                ret.data = new JSONObject(str);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return ret;
    }
}
