package com.yujun.asyncloadingusingvolley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 于军 on 2016/8/21.
 */
public class GsonUtil {

    public static List<News> getDataFromJson(String json) {
        List<News> list = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                News news = new News();
                news.setNewsUrl(object.getString("picSmall"));
                news.setNewsTitle(object.getString("name"));
                news.setNewsContent(object.getString("description"));
                list.add(news);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;

    }
}
