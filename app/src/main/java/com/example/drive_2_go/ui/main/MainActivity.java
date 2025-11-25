package com.example.drive_2_go.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.activity.EdgeToEdge;

import com.example.drive_2_go.ui.Client.description.DescriptionCarActivity;
import com.example.drive_2_go.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Récupérer le bouton
        Button switchBtn = findViewById(R.id.switchbtn);

        // Ajouter un listener pour ouvrir DescriptionCar
        switchBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DescriptionCarActivity.class);
            startActivity(intent);
        });
    }
}
