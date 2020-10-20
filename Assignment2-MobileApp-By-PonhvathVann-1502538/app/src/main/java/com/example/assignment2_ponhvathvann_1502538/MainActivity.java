package com.example.assignment2_ponhvathvann_1502538;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.application_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.mnuEx1:
                Intent volleyRequestExercise = new Intent(MainActivity.this, VolleyRequestExercise.class);
                startActivity(volleyRequestExercise);
                break;
            case R.id.mnuEx2:
                Intent fusedLocationServiceExercise = new Intent(MainActivity.this, FusedLocationServiceExercise.class);
                startActivity(fusedLocationServiceExercise);
                break;
            case R.id.mnuEx3:
                Intent MapsActivityExercise = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(MapsActivityExercise);
                break;
            case R.id.mnuEx4:
                Intent MiniProjectExercise = new Intent(MainActivity.this, MiniProject.class);
                startActivity(MiniProjectExercise);
                break;
            case R.id.mnuEx5:
                Intent intentExercise5 = new Intent(MainActivity.this, Exercise5.class);
                startActivity(intentExercise5);
                break;
            case R.id.mnuEx6:
                Intent intentExercise6 = new Intent(MainActivity.this, TabActivity.class);
                startActivity(intentExercise6);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}