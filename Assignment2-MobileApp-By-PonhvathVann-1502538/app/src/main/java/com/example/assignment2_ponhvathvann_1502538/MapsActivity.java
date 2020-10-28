package com.example.assignment2_ponhvathvann_1502538;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_LOCATION = 1 ;
    private FusedLocationProviderClient client;
    boolean requestLocation = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        client = LocationServices.getFusedLocationProviderClient(this);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.clear();

        // Add a marker in Auckland and move the camera
        LatLng auckland = new LatLng(-36.848461, 174.763336);
        mMap.addMarker(new MarkerOptions().position(auckland).title("Marker in Auckland"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(auckland));
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);

        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted? Ask for permission
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Show an explanation asynchronously and ask for permission
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_ACCESS_LOCATION);
            }
        } else {
            // Permission has already been granted, Do something else
            try{
                LocationRequest req = new LocationRequest();
                req.setInterval(2000); // 2 seconds
                req.setFastestInterval(500); // 500 milliseconds
                req.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

                client.requestLocationUpdates(req, new LocationCallback() {
                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        mMap.clear();

                        Location location = locationResult.getLastLocation();
                        double latitude = location.getLatitude();
                        double longitude = location.getLongitude();
                        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

                        Marker currentLocation = mMap.addMarker(new MarkerOptions().position(latLng)
                                .title("Current Location")
                                .snippet("Latitude: " + latitude + ", Longitude: " + longitude));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                        CameraPosition cameraPosition = new CameraPosition.Builder()
                                .target(latLng)
                                .zoom(15)
                                .build();
                        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                        currentLocation.showInfoWindow();
                    }
                }, null);

            }catch (SecurityException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_ACCESS_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    requestLocation = true;
                } else {
                    requestLocation = false;
                }
                return;
            }
        }
    }
}