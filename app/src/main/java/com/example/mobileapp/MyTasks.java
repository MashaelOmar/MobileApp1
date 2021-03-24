package com.example.mobileapp;

import java.util.List;

public class MyTasks {
    List<Task> myTasks;

    public MyTasks(List<Task> myTasks) {
        this.myTasks=myTasks;
    }
    public MyTasks() {
    }
    public MyTasks(Task task) {
        myTasks.add(task);
    }
    public List<Task> getMyTasks() {
        return myTasks;
    }

    public void setMyTasks(List<Task> myTasks) {
        this.myTasks = myTasks;
    }
}
