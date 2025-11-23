package com.example.drive_2_go.ui.main;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.drive_2_go.ui.start.GetStartedActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Démarre directement l'écran GetStarted
        Intent intent = new Intent(MainActivity.this, GetStartedActivity.class);
        startActivity(intent);

        // Empêche de revenir sur MainActivity
        finish();
    }
}
