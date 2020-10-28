package com.example.assignment2_ponhvathvann_1502538;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    ImageView imgViewRestaurant;
    ImageView imgViewMiniProject;
    ImageView imgViewLongAndLat;
    ImageView imgViewMapLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgViewRestaurant = findViewById(R.id.imgViewRestaurant);
        imgViewMiniProject = findViewById(R.id.imgViewMiniProject);
        imgViewLongAndLat = findViewById(R.id.imgViewLongLat);
        imgViewMapLocation = findViewById(R.id.imgViewGoogleMap);

        imgViewRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volleyRequestExercise = new Intent(MainActivity.this, VolleyRequestExercise.class);
                startActivity(volleyRequestExercise);
            }
        });

        imgViewMiniProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent MiniProjectExercise = new Intent(MainActivity.this, MiniProject.class);
                startActivity(MiniProjectExercise);
            }
        });

        imgViewLongAndLat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fusedLocationServiceExercise = new Intent(MainActivity.this, FusedLocationServiceExercise.class);
                startActivity(fusedLocationServiceExercise);
            }
        });

        imgViewMapLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent MapsActivityExercise = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(MapsActivityExercise);
            }
        });
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.application_menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//
//        switch (id) {
//            case R.id.mnuEx1:
//                Intent volleyRequestExercise = new Intent(MainActivity.this, VolleyRequestExercise.class);
//                startActivity(volleyRequestExercise);
//                break;
//            case R.id.mnuEx2:
//                Intent fusedLocationServiceExercise = new Intent(MainActivity.this, FusedLocationServiceExercise.class);
//                startActivity(fusedLocationServiceExercise);
//                break;
//            case R.id.mnuEx3:
//                Intent MapsActivityExercise = new Intent(MainActivity.this, MapsActivity.class);
//                startActivity(MapsActivityExercise);
//                break;
//            case R.id.mnuEx4:
//                Intent MiniProjectExercise = new Intent(MainActivity.this, MiniProject.class);
//                startActivity(MiniProjectExercise);
//                break;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//        return true;
//    }
}