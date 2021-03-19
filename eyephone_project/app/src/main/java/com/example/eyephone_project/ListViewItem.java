package com.example.eyephone_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ListViewItem {

    private String keyStr, titleStr ;

    public void setKey(String key) {
        keyStr = key ;
    }
    public void setTitle(String title) {
        titleStr = title ;
    }

    public String getKey() {
        return this.keyStr ;
    }
    public String getTitle() {
        return this.titleStr ;
    }
}