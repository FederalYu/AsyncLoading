package com.yujun.listviewpageloading;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoadListView.ILoadListener {

    private LoadListView listView;
    private MyAdapter adapter;
    private List<News> list = new ArrayList<>();
    private String url = "http://www.imooc.com/api/teacher?type=4&num=30";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        volley_Get_StringRequest();
        showListView(list);
    }

    private void showListView(List<News> list) {
        if (adapter == null) {
            listView = (LoadListView) findViewById(R.id.listView);
            listView.setInterface(this);
            adapter = new MyAdapter(this, list);
            listView.setAdapter(adapter);
        } else {
            adapter.onDataChange(list);
        }
    }

    @Override
    public void onLoad() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //获取更多数据
                volley_Get_StringRequest();
                //更新listview显示；
                showListView(list);
                //通知listview加载完毕
                listView.loadComplete();
            }
        }, 2000);
    }

    private void volley_Get_StringRequest() {
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                System.out.println(s);
                list = GsonUtil.getDataFromJson(s);
                adapter = new MyAdapter(MainActivity.this, list);
                listView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(MainActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
            }
        });
        request.setTag("newsGet");
        MyApplication.getHttpQueue().add(request);
    }
}
