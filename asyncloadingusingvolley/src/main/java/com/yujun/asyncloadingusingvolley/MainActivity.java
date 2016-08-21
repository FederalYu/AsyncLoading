package com.yujun.asyncloadingusingvolley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private List<News> mData;
    private String url = "http://www.imooc.com/api/teacher?type=4&num=30";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();

        volley_Get_StringRequest();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_main);
    }

    private void initData() {
        mData = new ArrayList<>();
    }

    private void volley_Get_StringRequest() {
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                System.out.println(s);
                mData = GsonUtil.getDataFromJson(s);
                mAdapter = new MyAdapter(MainActivity.this, mData);
                mRecyclerView.setAdapter(mAdapter);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
                mRecyclerView.setLayoutManager(linearLayoutManager);
                //设置RecyclerView的item间间隔
                mRecyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL_LIST));
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
