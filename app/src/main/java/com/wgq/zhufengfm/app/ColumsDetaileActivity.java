package com.wgq.zhufengfm.app;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import com.wgq.zhufengfm.app.model.*;
import com.wgq.zhufengfm.app.tasks.TaskCallback;
import com.wgq.zhufengfm.app.tasks.TaskResult;
//import com.wgq.zhufengfm.app.tasks.TestTask;
import com.wgq.zhufengfm.app.tasks.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;


public class ColumsDetaileActivity extends ActionBarActivity implements TaskCallback, AdapterView.OnItemClickListener {

    private ListView listView;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colums_detaile);
        listView = (ListView) findViewById(R.id.colums_detaile_block3);

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
                int count = detail.getData().size();
                List<String> list = new LinkedList<String>();
                for (int i = 0; i < count; i++) {
                    list.add(detail.getData().get(i).getPlayUrl64());
                }
                adapter = new ArrayAdapter(this,
                        android.R.layout.simple_list_item_1,
                        list);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(this);
//                Intent intent = new Intent();
//                Uri uri = Uri.parse("http://fdfs.xmcdn.com/group8/M00/54/E1/wKgDYFW7MFHRZw4zACMb_waTWvM450.mp3");
//                Uri uri1 = Uri.parse(detail.getData())
//                intent.setDataAndType(uri, "audio/*");
//                intent.setAction(Intent.ACTION_VIEW);
//                startActivity(intent);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView textView = (TextView) view;
        String uripath = textView.getText().toString();
        Intent intent = new Intent();
        Uri uri = Uri.parse(uripath);
        //Uri uri = Uri.parse("http://fdfs.xmcdn.com/group8/M00/54/E1/wKgDYFW7MFHRZw4zACMb_waTWvM450.mp3");
        intent.setDataAndType(uri, "audio/*");
        intent.setAction(Intent.ACTION_VIEW);
        startActivity(intent);
    }
}
