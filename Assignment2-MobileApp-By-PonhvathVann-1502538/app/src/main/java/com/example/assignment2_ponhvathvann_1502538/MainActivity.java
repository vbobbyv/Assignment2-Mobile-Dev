package com.example.assignment2_ponhvathvann_1502538;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;

public class MainActivity extends AppCompatActivity {
    RequestQueue queue = null;

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
                Intent intentExercise1 = new Intent(MainActivity.this, Exercise1.class);
                startActivity(intentExercise1);
                break;
            case R.id.mnuEx2:
                Intent intentExercise2 = new Intent(MainActivity.this, Exercise2.class);
                startActivity(intentExercise2);
                break;
            case R.id.mnuEx3:
                Intent intentExercise3 = new Intent(MainActivity.this, Exercise3.class);
                startActivity(intentExercise3);
                break;
            case R.id.mnuEx4:
                Intent intentExercise4 = new Intent(MainActivity.this, Exercise4.class);
                startActivity(intentExercise4);
                break;
            case R.id.mnuEx5:
                Intent intentExercise5 = new Intent(MainActivity.this, Exercise5.class);
                startActivity(intentExercise5);
                break;
            case R.id.mnuEx6:
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}