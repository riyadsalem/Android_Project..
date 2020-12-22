package com.example.myapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.ListItemRV;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListVh> {
    private Context context;
    private ArrayList<ListItemRV> lists = null;

    public ListAdapter(Context context, ArrayList<ListItemRV> lists) {
        this.context = context;
        this.lists = lists;
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }


    public class ListVh extends RecyclerView.ViewHolder {
        TextView NewList;

        public ListVh(@NonNull View view) {
            super(view);
            NewList = itemView.findViewById(R.id.txtNewList);
        }

        public void setData(final ListItemRV list) {
            NewList.setText(list.getNewList());
        }

    }


    @NonNull
    @Override
    public ListVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.listsviewcall, parent, false);
        return new ListVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListVh holder, int position) {
        holder.setData(lists.get(position));
        ListItemRV list = lists.get(position);
        holder.NewList.setText(list.getNewList());
    }




}