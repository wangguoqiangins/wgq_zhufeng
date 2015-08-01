package com.wgq.zhufengfm.app.util;

import android.graphics.BitmapFactory;

/**
 * Created by Administrator on 15-8-1.
 */
public class ImageUtil {
    private ImageUtil(){

    }

    /**
     * 图片采样比率计算，通过options中包含的图片原始尺寸，计算图片采样率
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if(reqHeight==0 || reqWidth==0){
            inSampleSize = 1;
            return inSampleSize;
        }
        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
}
