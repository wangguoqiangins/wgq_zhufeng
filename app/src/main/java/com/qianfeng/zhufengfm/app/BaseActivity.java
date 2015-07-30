package com.qianfeng.zhufengfm.app;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;

/**
 * Created with IntelliJ IDEA.
 * User: vhly[FR]
 * Date: 15/7/30
 * Email: vhly@163.com
 */

/**
 * 基础的Activity
 */
public class BaseActivity extends FragmentActivity {

    /**
     * 获取 startActivity 之后，新的Activity 进入的动画<br/>
     * 默认时从右往左，如果定制不同的动画，重写这个方法即可。
     * @return
     */
    protected int getEnterAnimationId(){
        return R.anim.anim_slide_to_left;
    }

    /**
     * 启动Activity, 并且给启动de Activity 指定一个动画
     * @param intent
     */
    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);

        overridePendingTransition(getEnterAnimationId(), 0);
    }

    protected int getExitAnimationId(){
        return R.anim.anim_drop_down;
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, getExitAnimationId());
    }
}
