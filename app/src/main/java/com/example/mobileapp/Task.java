package com.example.mobileapp;

public class Task {

    //task title
    public  String title;
    //Marked if task is priority
    public  String importance;
    // due date for the task
    public  String dueDate;
    // due time for the task
    public  String dueTime;


    public Task() {
        this("","","","");
    }

    /**
     * Create a new Task
     */

    public Task(String title, String importance, String dueDate,String dueTime) {
        this.title = title;
        this.importance = importance;
        this.dueDate = dueDate;
        this.dueDate = dueDate;
    }


    public String getDueTime() {
        return dueTime;
    }

    public String getTitle() {
        return title;
    }


    public String importance() {
        return importance;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public void setImportance(String importance) {
        this.importance = importance;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void setDueTime(String dueTime) {
        this.dueTime = dueTime;
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", dueTime='" + dueTime + '\'' +
                ", importance='" + importance + '\'' +
                '}';
    }
}