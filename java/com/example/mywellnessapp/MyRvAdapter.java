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

public class MyRvAdapter extends RecyclerView.Adapter<MyRvAdapter.MyHolder> {

    ArrayList<String> data;
    Context context;

    public MyRvAdapter(ArrayList<String> data, Context context) {
        this.data = data;
        this.context = context;

    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.new_rv_item, null,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.foodName.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        TextView foodName;

        public MyHolder(@NonNull View itemView){
            super(itemView);
            foodName = itemView.findViewById(R.id.ingredientName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Intent intent = new Intent(context,newIngredientList.class);
                    String value = data.get(position);
                    intent.putExtra("key",value);
                    context.startActivity(intent);
                }
            });
        }
    }

}
