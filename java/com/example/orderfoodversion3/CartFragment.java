package com.example.orderfoodversion3;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.orderfoodversion3.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class CartFragment extends Fragment {

    List<DataCart> dataCarts;
    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    CartAdapter adapter;
    ValueEventListener eventListener;
    TextView total ;
Button Order ;
Long somme;
    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //binding = ActivityMainBinding.inflate(getLayoutInflater());
        //setContentView(binding.getRoot());
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String userId = auth.getCurrentUser().getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference("users").child(userId).child("panier");

        View rootView = inflater.inflate(R.layout.fragment_cart, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerView);
        total=rootView.findViewById(R.id.total);
        Order = rootView.findViewById(R.id.Order);



    GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        dataCarts = new ArrayList<>();
        adapter = new CartAdapter(getActivity(), dataCarts); // Pass the Activity reference
        recyclerView.setAdapter(adapter);

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataCarts.clear();
                somme =0l ;
                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                    DataCart dataClass = itemSnapshot.getValue(DataCart.class);
                    dataCarts.add(dataClass);
                    Long p = dataClass != null ? dataClass.getFoodTotal() : 0L;
                   somme=somme+p;

                }
                total.setText("Total de commande : "+somme+" dt");
                // *********************************************
                Order.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        List<String> foodNameList = new ArrayList<>();
                        for (DataSnapshot panierSnapshot : snapshot.getChildren()) {
                            String foodName = panierSnapshot.child("foodName").getValue(String.class);
                            foodNameList.add(foodName);
                        }

// Create an Intent
                        Intent intent = new Intent(getActivity(), SaveAdress.class);
                        intent.putExtra("total", somme);
// Put the ArrayList as an extra in the Intent
                        intent.putStringArrayListExtra("foodNameList", (ArrayList<String>) foodNameList);

// Start the second activity
                        startActivity(intent);

                    }
                });

              /*  Order.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        Intent intent = new Intent(getActivity(), SaveAdress.class);
                        intent.putExtra("total",somme);
                        intent.putExtra("food",snapshot.child("foodName").getValue(String.class) );
                        intent.putExtra("Quantite",snapshot.child("foodQte").getValue(Integer.class));

                        startActivity(intent);
                    }


                });*/
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return rootView;
    }


}