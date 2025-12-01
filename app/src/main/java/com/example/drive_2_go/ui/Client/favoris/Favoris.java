package com.example.drive_2_go.ui.Client.favoris;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.drive_2_go.R;
import com.example.drive_2_go.ui.Client.accueil.AccueilActivity;
import com.example.drive_2_go.ui.Client.ptofil.Profil;

public class Favoris extends AppCompatActivity{

    private ImageButton buttonProfil;
    private ImageButton buttonHome;
    private ImageButton buttonFavoris;
    private ImageButton buttonHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoris);

        buttonProfil = findViewById(R.id.buttonProfil);
        buttonHome = findViewById(R.id.buttonHome);
        buttonFavoris = findViewById(R.id.buttonFavoris);
        buttonHistory = findViewById(R.id.buttonHistory);

        buttonProfil.setOnClickListener(v -> openProfil());
        buttonHome.setOnClickListener(v -> openAccueil());
        buttonFavoris.setOnClickListener(v -> Toast.makeText(this,"Déjà ici", Toast.LENGTH_SHORT).show());
        selectButton(buttonFavoris); // si tu es dans Favoris
    }

    private void selectButton(ImageButton button) {
        button.setSelected(true);
    }

    private void openProfil(){
        startActivity(new Intent(Favoris.this, Profil.class));
    }

    private void openAccueil(){
        startActivity(new Intent(Favoris.this, AccueilActivity.class));
    }

}

