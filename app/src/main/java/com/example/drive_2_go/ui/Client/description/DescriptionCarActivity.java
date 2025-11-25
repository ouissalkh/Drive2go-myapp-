package com.example.drive_2_go.ui.Client.description;

import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

import com.example.drive_2_go.R;

public class DescriptionCarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.description_car); // ton XML

        // Bouton retour
        ImageButton backBtn = findViewById(R.id.backbtn);
        backBtn.setOnClickListener(v -> finish()); // ferme cette activité et revient à MainActivity
    }
}
