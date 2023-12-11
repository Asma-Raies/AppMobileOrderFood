package com.example.orderfoodversion3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SaveAdress extends AppCompatActivity {
EditText saveadress ;
    Button showMaps ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_adress);
        saveadress=findViewById(R.id.saveAdresse);
        showMaps =findViewById(R.id.showMaps);

        setupLocationPicker(saveadress, "address");
        showMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveAdress();
            }
        });

    }

    private void saveAdress() {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String address = saveadress.getText().toString().trim();

        // Check if the user is authenticated
        if (auth.getCurrentUser() != null) {
            String userId = auth.getCurrentUser().getUid();
            DatabaseReference userAdress = database.child("users").child(userId).child("Order");
            String AdressItemId = userAdress.push().getKey();
            Intent intent = getIntent();
            ArrayList<String> receivedFoodNameList = intent.getStringArrayListExtra("foodNameList");
            AdressUser dataAddress = new AdressUser(address,getIntent().getExtras().getLong("total"),receivedFoodNameList);

            // Add the DataCart object to the user's cart under the unique key
            userAdress.child(AdressItemId).setValue(dataAddress);
            Toast.makeText(this, "Your order passsé en success", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(SaveAdress.this,MenuFragment.class);
            startActivity(i);
        }


    }
    private void setupLocationPicker(final EditText editText, final String locationType) {
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SaveAdress.this, MapsActivity.class);
                intent.putExtra("locationType", locationType);
                startActivityForResult(intent, MAPS_REQUEST_CODE);
            }
        });
    }





    private static final int MAPS_REQUEST_CODE = 100;

    private void openMaps(String locationType) {
        Intent intent = new Intent(SaveAdress.this, MapsActivity.class);
        intent.putExtra("locationType", locationType);
        startActivityForResult(intent, MAPS_REQUEST_CODE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == MAPS_REQUEST_CODE && data != null) {
            String selectedAddress = data.getStringExtra("selectedAddress");
            String locationType = data.getStringExtra("locationType");

             if ("address".equals(locationType)) {
                saveadress.setText(selectedAddress);
            }
        }
    }

}