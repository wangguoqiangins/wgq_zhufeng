package com.wgq.zhufengfm.app.tasks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import com.wgq.zhufengfm.app.cache.FileCache;
import com.wgq.zhufengfm.app.cache.MemoryCache;
import com.wgq.zhufengfm.app.client.HttpUtil;
import com.wgq.zhufengfm.app.util.ImageUtil;
import com.wgq.zhufengfm.app.util.MyLog;

/**
 * Created with IntelliJ IDEA.
 * User: vhly[FR]
 * Date: 15/7/31
 * Email: vhly@163.com
 */

/**
 * 下载图片
 */
public class ImageLoadTask extends AsyncTask<String, Integer, Bitmap> {

    public static final String TAG = "ImageTask....";
    /**
     * 当前任务要设置的目标
     */
    private ImageView imageView;
    private String url;

    public ImageLoadTask(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        Bitmap ret = null;

        if (params != null && params.length > 0) {
            url = params[0];

            ret = MemoryCache.getInstance().getBitmap(url);
            //如果没有获取到
            if(ret == null){
                byte[] data = FileCache.getInstance().loadFile(url);

                if (data == null) {
                    data = HttpUtil.doGet(url);
                    // 存文件
                    FileCache.getInstance().saveFile(url, data);
                }

                // 1. 文件缓存
                // 2. 图片二次采样
                // 3. 内存缓存

                if (data != null) {
                    // TODO 保存文件
                    // TODO 转换图片 bitmap
                    //TODO 进行二次采样
                    //1，只获取尺寸
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = true;
                    ret = BitmapFactory.decodeByteArray(data, 0, data.length,options);
                    MyLog.d(TAG,"first ret ="+ret);
                    MyLog.d(TAG,"mim"+options.outMimeType);
                    MyLog.d(TAG,"hei"+options.outHeight);
                    MyLog.d(TAG,"wid"+options.outWidth);
                    //2，计算原始尺寸与目标尺寸的采样比率

                    //3，设置options为实际解析图片，并且设置采样比率，能够缩小了
                    options.inJustDecodeBounds = false;
                    options.inPurgeable = true;
                    //设置解码器，可以使用的解码像素颜色的配置
                    //注意透明度与颜色的关系
                    //如果图片不能够使用这个配置，那么久自动使用自动的ARGB_8888
                    options.inPreferredConfig = Bitmap.Config.RGB_565;
                    options.inSampleSize = ImageUtil.calculateInSampleSize(options,imageView.getHeight(),imageView.getWidth());

                    ret = BitmapFactory.decodeByteArray(data, 0, data.length,options);
                    data = null;
                    MemoryCache.getInstance().putBitmap(url,ret);
                }
            }


        }

        return ret;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (bitmap != null) {
            if (imageView != null) {
                Object tag = imageView.getTag();
                if (tag != null) {
                    String str = null;
                    if (tag instanceof String) {
                        str = (String) tag;
                    }else if(tag instanceof String[]){
                        String[] ss = (String[]) tag;
                        str = ss[0];
                    }if(str.equals(url)){
                        imageView.setImageBitmap(bitmap);
                    }
                }

            }
        }
    }
}
