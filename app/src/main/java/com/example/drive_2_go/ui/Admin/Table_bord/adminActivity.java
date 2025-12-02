package com.example.drive_2_go.ui.Admin.Table_bord;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.drive_2_go.R;
import com.example.drive_2_go.ui.Admin.Gestion_Reservations.ReservationsActivity;
import com.example.drive_2_go.ui.main.MainActivity;

public class adminActivity extends AppCompatActivity {
    private Button btnreservation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin);


        btnreservation = findViewById(R.id.reservationbtn);
        btnreservation.setOnClickListener(v -> {
            Intent intent = new Intent(adminActivity.this, ReservationsActivity.class);
            startActivity(intent);

        });

    }
}