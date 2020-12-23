package com.example.myapplication.model;

public class TaskItemRV {
    String Title;
    Boolean isChecked;

    public TaskItemRV() {
    }

    public TaskItemRV(String title, Boolean isChecked) {
        Title = title;
        this.isChecked = isChecked;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Boolean getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(Boolean checked) {
        isChecked = checked;
    }
}