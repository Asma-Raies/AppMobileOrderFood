package com.example.orderfoodversion3;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private List<DataCart> dataList;
    private Context context;

    public CartAdapter(Context context, List<DataCart> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Glide.with(context).load(dataList.get(position).getImage()).into(holder.recyclerImage);
        holder.recTitle.setText(dataList.get(position).getFoodName());
        // holder.rectotal.setText(dataList.get(position).getFoodName());
        holder.recQte.setText(dataList.get(position).getFoodQte()+" ");
        holder.recPrix.setText("total:"+dataList.get(position).getFoodTotal()+"dt");
       // Intent i =new Intent(this,CartFragment.class);



    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView recTitle,recQte , recPrix;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recTitle = itemView.findViewById(R.id.recTitle);
            recQte = itemView.findViewById(R.id.recQte);
            recPrix = itemView.findViewById(R.id.recPrix);

        }
    }
}
