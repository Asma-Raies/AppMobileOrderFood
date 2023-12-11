package com.example.orderfoodversion3;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MenuFragment extends Fragment {

    ArrayList<DataFood> dataFoods;
    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    MyAdapter adapter;

    public MenuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        databaseReference = FirebaseDatabase.getInstance().getReference("categories");
        View rootView = inflater.inflate(R.layout.fragment_menu, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        dataFoods = new ArrayList<>();
        adapter = new MyAdapter(getActivity(), dataFoods); // Pass the Activity reference
        recyclerView.setAdapter(adapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataFoods.clear(); // Clear the list before adding new data
               /* for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    // Assuming the "prix" field is a String in the database
                    /*String prixAsString = snapshot.child("prix").getValue(String.class);

                    try {
                        int prix = Integer.parseInt(prixAsString);
                        // Now, create a new DataFood object with the converted prix
                        DataFood dataClass = new DataFood();
                        dataClass.setNom(dataSnapshot.child("nom").getValue(String.class));
                        dataClass.setDescription(dataSnapshot.child("description").getValue(String.class));
                        dataClass.setImage(dataSnapshot.child("image").getValue(String.class));
                        dataClass.setPrix(prix);

                        dataFoods.add(dataClass);
                    } catch (NumberFormatException e) {
                        // Handle the exception if the conversion fails
                        e.printStackTrace();
                    }
                }*/
                    for (DataSnapshot itemSnapshot: snapshot.getChildren()){
                        DataFood dataClass = itemSnapshot.getValue(DataFood.class);
                        dataFoods.add(dataClass);}

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
