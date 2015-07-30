package com.qianfeng.zhufengfm.app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class Test1Activity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, R.anim.anim_drop_down);
    }
}
