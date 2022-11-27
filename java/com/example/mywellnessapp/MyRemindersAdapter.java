package com.example.mywellnessapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyRemindersAdapter extends RecyclerView.Adapter<MyRemindersAdapter.MyViewHolder> {

    ArrayList<String> arrayList;
    Context context;
    DBReminderHelper DB;

    public MyRemindersAdapter(ArrayList<String> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
        DB = new DBReminderHelper(context);
    }


    @NonNull
    @Override
    public MyRemindersAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View v = layoutInflater.inflate(R.layout.remindblob,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRemindersAdapter.MyViewHolder holder, int position) {
        Cursor res = DB.getdata();
        while (res.moveToNext()){
            if (res.getString(0).equals(arrayList.get(position))){
                holder.idDate.setText(res.getString(1));
                holder.idTime.setText(res.getString(2));
            }
        }
        holder.reminderName.setText(arrayList.get(position).toString());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView reminderName,idDate,idTime;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            reminderName = itemView.findViewById(R.id.reminderName);
            idDate = itemView.findViewById(R.id.idDate);
            idTime = itemView.findViewById(R.id.idTime);

        }

    }



}
