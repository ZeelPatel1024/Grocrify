package com.example.mywellnessapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyIngerdientAdapter extends RecyclerView.Adapter<MyIngerdientAdapter.MyHolder> {

    ArrayList<String> data;
    Context context;

    public MyIngerdientAdapter(ArrayList<String> data, Context context) {
        this.data = data;
        this.context = context;

    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.new_ingredient_item, null,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.ingredientName.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        TextView ingredientName;

        public MyHolder(@NonNull View itemView){
            super(itemView);
            ingredientName = itemView.findViewById(R.id.ingredientName);
        }
    }

}
