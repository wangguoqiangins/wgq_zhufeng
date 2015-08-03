package com.wgq.zhufengfm.app;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import com.wgq.zhufengfm.app.model.ColumsDetail;
import com.wgq.zhufengfm.app.tasks.TaskCallback;
import com.wgq.zhufengfm.app.tasks.TaskResult;
import com.wgq.zhufengfm.app.tasks.TestTask;
import org.json.JSONException;
import org.json.JSONObject;


public class Test1Activity extends BaseActivity implements TaskCallback {
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);

        setTitle("测试");


        Intent intent = new Intent();
        Uri uri = Uri.parse("http://fdfs.xmcdn.com/group8/M00/54/E1/wKgDYFW7MFHRZw4zACMb_waTWvM450.mp3");
        intent.setDataAndType(uri, "audio/*");
        intent.setAction(Intent.ACTION_VIEW);
        startActivity(intent);


        textView = (TextView) findViewById(R.id.test_for_txt);
        Intent intent1 = getIntent();
        String detailpath = intent1.getStringExtra("detailpath");
        new TestTask(this).execute(detailpath);


    }


    @Override
    public void onTaskFinished(TaskResult result) {
        if(result.taskId == Constants.TEST_RESULT){
            String sdata = (String) result.data;
            try {
                JSONObject jsonObject = new JSONObject(sdata);
                ColumsDetail detail = new ColumsDetail();
                detail.parseColumdetail(jsonObject);
                textView.setText(detail.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


    }
}
