package com.example.orderfoodversion3;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends  RecyclerView.Adapter<OrderAdapter.OrderViewHolder>{

private List<String> dataList;
private Context context;
public OrderAdapter(Context context,List<String> dataList){
        this.context=context;
        this.dataList=dataList;
        }
@NonNull
@Override
public OrderAdapter.OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType){
        View view=LayoutInflater.from(context).inflate(R.layout.order_item,parent,false);
        return new OrderAdapter.OrderViewHolder(view);
        }
    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.OrderViewHolder holder, int position) {

           String foods=dataList.get(position);
           holder.recTitle.setText(foods);

    }
@Override
public int getItemCount(){
        return dataList.size();
        }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {

        TextView recTitle;
        CardView recCard;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            recTitle = itemView.findViewById(R.id.recTitle); // Corrected ID here
            recCard = itemView.findViewById(R.id.recCard);
        }
    }
    }

