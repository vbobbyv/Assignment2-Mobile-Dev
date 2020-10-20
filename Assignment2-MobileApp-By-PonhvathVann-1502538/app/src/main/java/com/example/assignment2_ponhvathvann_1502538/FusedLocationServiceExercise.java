package com.example.assignment2_ponhvathvann_1502538;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class FusedLocationServiceExercise extends AppCompatActivity {
    private static final int REQUEST_LOCATION = 99;
    private FusedLocationProviderClient client;
    Button btnGetCurrentLocation;
    TextView txtViewLatitude;
    TextView txtViewLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fused_location_service_exercise);

        btnGetCurrentLocation = findViewById(R.id.btnGetCurrentLocation);
        txtViewLatitude = findViewById(R.id.textViewLatitudeValue);
        txtViewLongitude = findViewById(R.id.textViewLongitudeValue);

        client = LocationServices.getFusedLocationProviderClient(this);

        btnGetCurrentLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(FusedLocationServiceExercise.this,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
                    // Permission is not granted? Ask for permission
                    if (ActivityCompat.shouldShowRequestPermissionRationale(
                            FusedLocationServiceExercise.this,
                            Manifest.permission.ACCESS_FINE_LOCATION)) {
                        // Show an explanation asynchronously and ask for permission
                    } else { // No explanation needed; request the permission
                        ActivityCompat.requestPermissions(
                                FusedLocationServiceExercise.this,
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                REQUEST_LOCATION);
                    }
                } else { // Permission has already been granted
                    try {
                        Task<Location> currentLocation = client.getLastLocation();

                        currentLocation.addOnCompleteListener(new OnCompleteListener<Location>() {
                            @Override
                            public void onComplete(@NonNull Task<Location> task) {
                                String latitude = Double.toString(task.getResult().getLatitude());
                                String longitude = Double.toString(task.getResult().getLongitude());

                                txtViewLatitude.setText("Current Latitude: " + latitude);
                                txtViewLongitude.setText("Current Longitude: " + longitude);
                            }
                        });
                    }catch (SecurityException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

//        LocationRequest req = new LocationRequest();
//        req.setInterval(2000);
//        req.setFastestInterval(500);
//        req.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // Permission is not granted? Ask for permission
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
//                // Show an explanation asynchronously and ask for permission
//            } else {
//                // No explanation needed; request the permission
//                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
//            }
//        } else {
//            // Permission has already been granted
//            client.requestLocationUpdates(req,new LocationCallback(){
//                @Override
//                public void onLocationResult(LocationResult locationResult) {
//                    Log.e("location:", locationResult.getLastLocation().toString());
//                    System.out.println(locationResult.getLastLocation().getLatitude());
//                    System.out.println(locationResult.getLastLocation().getLongitude());
//
//                    Toast.makeText(FusedLocationServiceExercise.this, locationResult.getLastLocation().toString(), Toast.LENGTH_LONG).show();
//                }
//            },null);
//        }
    }
}