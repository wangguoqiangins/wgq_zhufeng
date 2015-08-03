package com.wgq.zhufengfm.app;

import android.app.Application;
import com.wgq.zhufengfm.app.cache.FileCache;

/**
 * Created with IntelliJ IDEA.
 * User: wgq
 * Date: 15/7/31
 */
public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        FileCache.createInstance(getApplicationContext());

    }
}
