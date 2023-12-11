package com.example.orderfoodversion3;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,GoogleMap.OnMapClickListener {

    GoogleMap gMap;
    FrameLayout map;
    String address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        map = findViewById(R.id.map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.gMap = googleMap;
        LatLng latLng = new LatLng(36.8065, 10.1815);
        this.gMap.addMarker(new MarkerOptions().position(latLng).title("Marker"));
        this.gMap.moveCamera(com.google.android.gms.maps.CameraUpdateFactory.newLatLng(latLng));

        this.gMap.setOnMapClickListener(this);



    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
    /*@Override
    public void onMapClick(LatLng latLng) {
        System.out.println("onMapClick");


        double clickedLatitude = latLng.latitude;
        double clickedLongitude = latLng.longitude;
        // In  Maps activity, when the user selects a location
        Intent returnIntent = new Intent();
        returnIntent.putExtra("selectedAddress", address);
        returnIntent.putExtra("locationType", getIntent().getStringExtra("locationType")); // Retrieve and send back the location type
        setResult(Activity.RESULT_OK, returnIntent);
        finish();



        try {
            Geocoder geocoder = new Geocoder(Maps.this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(clickedLatitude, clickedLongitude, 1);
            address = addresses.get(0).getAddressLine(0);
            localite = addresses.get(0).getLocality();
            city = addresses.get(0).getAdminArea();
            country = addresses.get(0).getCountryName();
            String postalcode = addresses.get(0).getPostalCode();
            String knownname = addresses.get(0).getFeatureName();

            Toast.makeText(this, "" + address , Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            // Handle the exception here
            Toast.makeText(this, "Error retrieving address information: " + e.getMessage(), Toast.LENGTH_SHORT).show();

        }

    }*/




    @Override
    public void onMapClick(LatLng latLng) {
        try {
            Geocoder geocoder = new Geocoder(MapsActivity.this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);

            if (addresses == null || addresses.isEmpty()) {
                Toast.makeText(this, "No address found for the location.", Toast.LENGTH_SHORT).show();
                return;
            }

            address = addresses.get(0).getAddressLine(0);

            Intent returnIntent = new Intent();
            returnIntent.putExtra("selectedAddress", address);
            returnIntent.putExtra("locationType", getIntent().getStringExtra("locationType"));
            setResult(Activity.RESULT_OK, returnIntent);
            finish();

        } catch (IOException e) {
            Toast.makeText(this, "Error retrieving address: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }





}