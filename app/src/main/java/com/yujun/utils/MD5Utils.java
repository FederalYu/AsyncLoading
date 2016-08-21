package com.yujun.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by 于军 on 2016/8/10.
 */
public class MD5Utils {

    public static String encode(String string) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] result = digest.digest(string.getBytes());
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : result) {
                int num = b & 0xff;// 这里的是为了将原本是byte型的数向上提升为int型，从而使得原本的负数转为了正数
                String hex = Integer.toHexString(num);//这里将int型的数直接转换成16进制表示
                //16进制可能是为1的长度，这种情况下，需要在前面补0
                if (hex.length() == 1) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(hex);
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
