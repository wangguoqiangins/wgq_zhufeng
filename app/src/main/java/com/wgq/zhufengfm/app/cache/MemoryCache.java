package com.wgq.zhufengfm.app.cache;

import android.graphics.Bitmap;
import android.os.Build;
import android.support.v4.util.LruCache;
import com.wgq.zhufengfm.app.util.MyLog;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by Administrator on 15-8-1.
 */
public class MemoryCache {
    private static MemoryCache ourInstance ;

    public static MemoryCache getInstance() {
        if (ourInstance == null) {
            ourInstance = new MemoryCache();
        }
        return ourInstance;
    }

    /**
     * 使用LRU算法的，可以指定内存最大限制的图片缓存，相当于一个hashmap
     */
    private LruCache<String,Bitmap> lruCache;

    //使用软引用的存储区，通过这种方式扩大内存利用率
    private HashMap<String,SoftReference<Bitmap>> softCache;

    private MemoryCache() {
        //设置内存的尺寸，通常都是最大内存数/8
        int memorySize = (int) (Runtime.getRuntime().maxMemory()/8);
        //使用内存字节数的情况，需要重写sizeof方法

        lruCache = new LruCache<String,Bitmap>(memorySize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                int ret = 0;
                if(Build.VERSION.SDK_INT >=19){
                    ret = value.getAllocationByteCount();
                }else if(Build.VERSION.SDK_INT >=12){
                    ret = value.getByteCount();
                }else {
                    //对于低于12的版本，需要计算 width + height * 颜色字节数
                    //一行像素所占用的字节数
                    int rowBytes = value.getRowBytes();
                    //高度就是行数
                    int bitmapHeight = value.getHeight();
                    ret = rowBytes * bitmapHeight;
                }
                return ret;
            }

        };
        softCache = new LinkedHashMap<String, SoftReference<Bitmap>>();
    }

    /**
     * 获取缓存的图片
     * @param url
     * @return
     */
    public Bitmap getBitmap(String url){
        Bitmap ret = null;
        if(url!=null){
            ret = lruCache.get(url);
            if(ret == null){
                SoftReference<Bitmap> reference = softCache.get(url);//softcache还未初始化
                if(reference != null){
                    //获取软引用指向的数据
                    ret = reference.get();
                    if(ret != null){
                        lruCache.put(url,ret);
                    }
                }

            }
        }
        return ret;
    }
    public void putBitmap(String url,Bitmap data){
        if (url != null && data != null){
            lruCache.put(url,data);
            //更新软引用缓存
            softCache.put(url,new SoftReference<Bitmap>(data));
            MyLog.d("MemoryCache","lru Size"+lruCache.size());
        }
    }

}
