package com.example.orderfoodversion3;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.appcompat.app.WindowDecorActionBar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<DataFood> dataList;
    private Context context;
    public MyAdapter(Context context, ArrayList<DataFood> dataList) {
        this.context = context;
        this.dataList = dataList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.food_item, parent, false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(dataList.get(position).getImage()).into(holder.recyclerImage);
        holder.recyclerCaption.setText(dataList.get(position).getNom());



        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("Image", dataList.get(holder.getAdapterPosition()).getImage());
                intent.putExtra("Description", dataList.get(holder.getAdapterPosition()).getDescription());
                intent.putExtra("Title", dataList.get(holder.getAdapterPosition()).getNom());
                intent.putExtra("prix",dataList.get(holder.getAdapterPosition()).getPrix());
                //intent.putExtra("Key",dataList.get(holder.getAdapterPosition()).getKey());
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return dataList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView recyclerImage;
        TextView recyclerCaption;
        CardView recCard;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerImage = itemView.findViewById(R.id.recyclerImage);
            recyclerCaption = itemView.findViewById(R.id.recyclerCaption);
            recCard = itemView.findViewById(R.id.recCard);
        }
    }
}
