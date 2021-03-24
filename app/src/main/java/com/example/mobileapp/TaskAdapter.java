package com.example.mobileapp;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class TaskAdapter extends BaseAdapter {
    Activity myActivity;
    MyTasks myTasks;

    public TaskAdapter(Activity myActivity, MyTasks myTasks) {
        this.myActivity = myActivity;
        this.myTasks = myTasks;
    }

    @Override
    public int getCount() {
        return myTasks.getMyTasks().size();
    }

    @Override
    public Object getItem(int position) {
        return myTasks.getMyTasks().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
