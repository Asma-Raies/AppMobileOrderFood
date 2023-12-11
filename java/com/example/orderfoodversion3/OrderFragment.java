package com.example.orderfoodversion3;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class OrderFragment extends Fragment {
    List<String> dataOrder;
    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    OrderAdapter adapter;
    ValueEventListener eventListener;
    TextView adresse, Total;
    Button Order;
    Long somme;
    AdressUser data;

    public OrderFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize dataOrder here
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String userId = auth.getCurrentUser().getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference("users").child(userId).child("Order");

        View rootView = inflater.inflate(R.layout.fragment_order, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerView);
        adresse = rootView.findViewById(R.id.textViewAddress);
        Total = rootView.findViewById(R.id.textViewTotal);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        dataOrder = new ArrayList<>();

        adapter = new OrderAdapter(getActivity(), dataOrder); // Pass the Activity reference
        recyclerView.setAdapter(adapter);

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataOrder.clear();
                somme = 0L;
                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                    AdressUser data = itemSnapshot.getValue(AdressUser.class);
                      for (String i : data.getFoodNameList()){
                        dataOrder.add(i);
                      }



                    // Consider removing this local variable declaration to use the global 'somme'
                    Long somme = itemSnapshot.child("total").getValue(Long.class);
                    Total.setText("Total de commande : " + somme + " dt");
                    adresse.setText("Delivery adresse :  "+itemSnapshot.child("addresse").getValue(String.class));
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle onCancelled
            }
        });

        return rootView;
    }
}
