package com.example.myapplication.model;
//////////////////////////////////  complete   //////////////////////////////////

public class TaskItemRV {
    String Title;
    Boolean isChecked;
    String  Date;
    String Description;
   public String idTask;

    public TaskItemRV() { }

    public TaskItemRV(String title, Boolean isChecked) {
        Title = title;
        this.isChecked = isChecked;
    }


    public Boolean getIsChecked() { return isChecked; }
    public void setIsChecked(Boolean checked) { this.isChecked = checked; }

    public String getTitle() { return Title; }
    public void setTitle(String title) { Title = title; }


    public String getDate() { return Date; }
   public void setDate(String date) { Date = date; }

   public String getDescription() { return Description; }
   public void setDescription(String description) { Description = description; }

    public String getIdTask() { return idTask; }
    public void setIdTask(String idTask) { this.idTask = idTask; }
}