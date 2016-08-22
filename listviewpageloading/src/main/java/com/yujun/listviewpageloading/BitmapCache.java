package com.yujun.listviewpageloading;

import android.graphics.Bitmap;
import android.os.Build;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by 于军 on 2016/8/21.
 */
public class BitmapCache implements ImageLoader.ImageCache {
    private LruCache<String, Bitmap> cache;
    //设置内存缓存最大为10M
    private int maxSize = (int) (Runtime.getRuntime().maxMemory() / 8);

    public BitmapCache() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
            cache = new LruCache<String, Bitmap>(maxSize) {
                @Override
                protected int sizeOf(String key, Bitmap value) {
                    return value.getRowBytes() * value.getHeight();
                }
            };
        }
    }

    @Override
    public Bitmap getBitmap(String s) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
            return cache.get(s);
        }
        return null;
    }

    @Override
    public void putBitmap(String s, Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
            cache.put(s, bitmap);
        }
    }
}
