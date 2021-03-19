package com.example.eyephone_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private ListView listview ;
    private ListViewAdapter adapter;
    List<Bbs> datas = new ArrayList<>();
    Context context;
    LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Intent intent = getIntent();
        String[] str = intent.getExtras().getStringArray("title");
        adapter = new ListViewAdapter();

        listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(adapter);
        for(int i =0;i<5;i++) {
            adapter.addItem(str[i]);
        }

        adapter.notifyDataSetChanged();
    }
    public void setarray(List<Bbs> datas) {
        System.out.println("**************************************");
        this.datas = datas;
    }
}