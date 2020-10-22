package com.example.assignment2_ponhvathvann_1502538;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.api.Status;
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
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MiniProject extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener {

    private GoogleMap mMap;
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_LOCATION = 1 ;
    private FusedLocationProviderClient client;
    private boolean requestLocation = false;
    private double currentLatitude;
    private double currentLongitude;
    AutocompleteSupportFragment autocompleteFragment;
    RequestQueue queue = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mini_project);

        autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        client = LocationServices.getFusedLocationProviderClient(this);

        queue = Volley.newRequestQueue(getApplicationContext());

        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), "AIzaSyCCvZDYG9_zmCFqsXqDXM1zXIIf0DYnNFg");
        }

        // Specify the types of place data to return.
        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ADDRESS, Place.Field.LAT_LNG, Place.Field.NAME));

        // Set up a PlaceSelectionListener to handle the response.
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NotNull Place place) {
                // TODO: Get info about the selected place.
                Log.i("Search", "Place: " + place.getName() + ", " + place.getId());
                System.out.println(place.getAddress());
                System.out.println(place.getName());
                System.out.println(place.getLatLng());

                LatLng latLng = place.getLatLng();
                String locationName = place.getName();
                String address = place.getAddress();

                mMap.clear();
                Marker marker = mMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title(locationName)
                        .snippet(address)
                );
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));

                mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

                    // Use default InfoWindow frame
                    @Override
                    public View getInfoWindow(Marker arg0) {
                        return null;
                    }

                    // Defines the contents of the InfoWindow
                    @Override
                    public View getInfoContents(Marker arg0) {

                        // Getting view from the layout file infowindowlayout.xml
                        View customInfoWindow = getLayoutInflater().inflate(R.layout.infowindow, null);

                        TextView tvName = (TextView) customInfoWindow.findViewById(R.id.txtViewName);
                        TextView tvAddress = (TextView) customInfoWindow.findViewById(R.id.txtViewAddress);

                        LatLng latLng = arg0.getPosition();
                        String title=arg0.getTitle();
                        String address=arg0.getSnippet();

                        tvName.setText(title);
                        tvAddress.setText(address);

                        return customInfoWindow;
                    }
                });
            }

//            public boolean onMarkerClick(final Marker marker) {
//
//                if (marker.equals(Somewhere))
//                {
//                    markerclicked=1;
//                    return true;
//                }
//                return false;
//
//                marker.showInfoWindow();
//            }


            @Override
            public void onError(@NotNull Status status) {
                // TODO: Handle the error.
                Log.i("Search", "An error occurred: " + status);
            }
        });

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
//                req.setInterval(2000); // 2 seconds
//                req.setFastestInterval(500); // 500 milliseconds
//                req.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

                client.requestLocationUpdates(req, new LocationCallback() {
                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        mMap.clear();
                        Location location = locationResult.getLastLocation();

                        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

                        currentLatitude = location.getLatitude();
                        currentLongitude = location.getLongitude();
//                        Marker myLocation = mMap.addMarker(new MarkerOptions().position(latLng));

                        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                        CameraPosition cameraPosition = new CameraPosition.Builder()
                                .target(latLng)
                                .zoom(15)
                                .build();
                        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                        mMap.setMyLocationEnabled(true);
                        mMap.setOnMyLocationButtonClickListener(MiniProject.this);
                        mMap.setOnMyLocationClickListener(MiniProject.this);



                    }
                }, null);

            }catch (SecurityException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void findNearbyRestaurant(String lat, String lng, int radius) {
        String rootUrl = "https://developers.zomato.com/api/v2.1/search?";
        String location = "lat=" + lat + "&lon=" + lng + "&radius=" + radius;
        String url = rootUrl + location;

        System.out.println(url);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray data = response.getJSONArray("restaurants");

                            for(int index = 0; index < data.length(); index++)
                            {
                                JSONObject restaurant_info = data.getJSONObject(index);
                                JSONObject restaurant = restaurant_info.getJSONObject("restaurant");
                                JSONObject location = restaurant.getJSONObject("location");

                                String restaurant_name = restaurant.getString("name");
                                String restaurant_latitude = location.getString("latitude");
                                String restaurant_longitude = location.getString("longitude");

                                LatLng latLng = new LatLng(Double.parseDouble(restaurant_latitude), Double.parseDouble(restaurant_longitude));
                                mMap.addMarker(new MarkerOptions().position(latLng).title(restaurant_name));

                                System.out.println(restaurant_name);
                                System.out.println(restaurant_latitude);
                                System.out.println(restaurant_longitude);

                            }
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("error",error.getLocalizedMessage());
                    }
                })
                {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("Content-Type", "application/json; charset=UTF-8");
                        params.put("user-key", "7e0ecb646b8b71ac0cd781e4ad519efb");
                        return params;
                    }
                };
        queue.add(jsObjRequest);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions,
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

    @Override
    public boolean onMyLocationButtonClick() {
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
//                req.setInterval(2000); // 2 seconds
//                req.setFastestInterval(500); // 500 milliseconds
//                req.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

                client.requestLocationUpdates(req, new LocationCallback() {
                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        mMap.clear();
                        Location location = locationResult.getLastLocation();

                        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

                        currentLatitude = location.getLatitude();
                        currentLongitude = location.getLongitude();
//                        Marker myLocation = mMap.addMarker(new MarkerOptions().position(latLng));

                        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                        CameraPosition cameraPosition = new CameraPosition.Builder()
                                .target(latLng)
                                .zoom(15)
                                .build();
                        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                        mMap.setMyLocationEnabled(true);
                        mMap.setOnMyLocationButtonClickListener(MiniProject.this);
                        mMap.setOnMyLocationClickListener(MiniProject.this);
                        findNearbyRestaurant(String.valueOf(currentLatitude), String.valueOf(currentLongitude), 500);



                    }
                }, null);

            }catch (SecurityException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
    }
}