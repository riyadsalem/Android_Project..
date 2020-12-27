package com.example.myapplication.model;

public class ListItemRV {

    String NewList;
    String id;
   // int CounterTasks;

    public ListItemRV() { }

    public ListItemRV(String newList) { NewList = newList; }


    public  String getNewList() { return NewList; }
    public void setNewList(String newList) { NewList = newList; }


    public  String getId() { return id; }
    public void setId(String id) { this.id = id; }

   // public int getCounterTasks() { return CounterTasks; }

  //  public void setCounterTasks(int counterTasks) { CounterTasks = counterTasks; }
}