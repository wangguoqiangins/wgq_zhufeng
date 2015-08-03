package com.wgq.zhufengfm.app.tasks;

import android.os.AsyncTask;
import com.wgq.zhufengfm.app.Constants;
import com.wgq.zhufengfm.app.client.HttpUtil;

/**
 * Created by Administrator on 15-8-2.
 */
public class TestTask extends BaseTask{

    public TestTask(TaskCallback callback) {
        super(callback);
    }

    @Override
    protected TaskResult doInBackground(String... params) {
        TaskResult result = new TaskResult();
        result.taskId = Constants.TEST_RESULT;
        byte[] bytes = HttpUtil.doGet(params[0]);
        String s = new String(bytes, 0, bytes.length);
        result.data = s;

        return result;
    }
}


