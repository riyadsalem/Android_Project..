package com.example.myapplication.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.List;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private Context context;
    private ArrayList<List> arrList;

    public ListAdapter(Context context, ArrayList<List> arrList) {
        this.context = context;
        this.arrList = arrList;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtNewList;
        public ViewHolder(@NonNull View view, Context c) {
            super(view);
            txtNewList = itemView.findViewById(R.id.txtNewList);
        }
    }




    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listsviewcall, parent, false);
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        List list = arrList.get(position);
        holder.txtNewList.setText(list.getListName());
    }

    @Override
    public int getItemCount() {
        return arrList.size();
    }


}