package com.example.mobileapp;


import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileapp.Task;
import com.example.mobileapp.ClickListener;

import java.net.StandardSocketOptions;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import java.text.SimpleDateFormat;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private List<Task> TList;
    private Context context;
    ClickListener listener;

    public TaskAdapter(Context context, List<Task> TList, ClickListener listener){
        this.TList = TList;
        this.context = context;
        this.listener=listener;

    }


    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.taskviewholder, parent, false);
        final TaskAdapter.TaskViewHolder mv = new TaskAdapter.TaskViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.OnItemClick(view , mv.getAdapterPosition());
            }
        });
        return  mv;

    }
    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task reim = TList.get(position);
        holder.remTitle.setText(reim.getTitle());
        holder.remDate.setText(reim.getDueDate());
        holder.remTime.setText(reim.getDueTime());



    }//onBindViewHolder

    @Override
    public int getItemCount() {
        return TList.size();
    }


    class TaskViewHolder extends RecyclerView.ViewHolder {

        TextView remTitle, remDate, remTime, remImpo;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);

            remTitle = itemView.findViewById(R.id.HTitle);
            remDate=itemView.findViewById(R.id.HDate);
            remTime=itemView.findViewById(R.id.HTime);


        }

    }//end ChatHolder

}






