package com.yujun.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 三层缓存之本地缓存
 * Created by 于军 on 2016/8/10.
 */
public class LocalCacheUtils {
    private static final String CACHE_PATH =
            Environment.getExternalStorageDirectory().getAbsolutePath() + "/images";

    /**
     * 从本地缓存中读取图片
     *
     * @param url
     * @return
     */
    public Bitmap getBitmapFromLocalCache(String url) {
        //把图片的url当做文件名,并进行MD5加密
        String fileName = MD5Utils.encode(url);
        File file = new File(CACHE_PATH, fileName);
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
            return bitmap;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * 向本地缓存中存入图片
     *
     * @param url
     * @param bitmap
     */
    public void setBitmapToLocalCache(String url, Bitmap bitmap) {
        //把图片的url当做文件名,并进行MD5加密
        String fileName = MD5Utils.encode(url);
        File file = new File(CACHE_PATH, fileName);
        //通过得到文件的父文件,判断父文件是否存在
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        //把文件保存至本地
        try {
            file.createNewFile();
            //compress:压缩
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
