package com.wgq.zhufengfm.app;

import android.app.Application;
import com.wgq.zhufengfm.app.cache.FileCache;

/**
 * Created with IntelliJ IDEA.
 * User: vhly[FR]
 * Date: 15/7/31
 * Email: vhly@163.com
 */
public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        FileCache.createInstance(getApplicationContext());

    }
}
