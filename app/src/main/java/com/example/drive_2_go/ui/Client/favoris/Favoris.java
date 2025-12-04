package com.example.drive_2_go.ui.Client.favoris;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drive_2_go.R;
import com.example.drive_2_go.data.model.Car;
import com.example.drive_2_go.ui.Client.accueil.AccueilActivity;
import com.example.drive_2_go.ui.Client.history.HistoryActivity;
import com.example.drive_2_go.ui.Client.profil.Profil;

import java.util.ArrayList;
import java.util.List;

public class Favoris extends AppCompatActivity{

    private ImageButton buttonProfil;
    private ImageButton buttonHome;
    private ImageButton buttonFavoris;
    private ImageButton buttonHistory;
    RecyclerView rvFavorites;
    ImageView ivEmptyImage;

    List<Car> allCars = new ArrayList<>();
    List<Car> favoriteCars = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoris);

        rvFavorites = findViewById(R.id.rv_favorites);
        ivEmptyImage = findViewById(R.id.iv_empty_image);

        rvFavorites.setLayoutManager(new LinearLayoutManager(this));
        checkEmptyState();

        buttonProfil = findViewById(R.id.buttonProfil);
        buttonHome = findViewById(R.id.buttonHome);
        buttonFavoris = findViewById(R.id.buttonFavoris);
        buttonHistory = findViewById(R.id.buttonHistory);

        buttonProfil.setOnClickListener(v -> openProfil());
        buttonHome.setOnClickListener(v -> openAccueil());
        buttonFavoris.setOnClickListener(v -> Toast.makeText(this,"Déjà ici", Toast.LENGTH_SHORT).show());
        buttonHistory.setOnClickListener(v -> openHistory());
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
    private void openHistory(){
        startActivity(new Intent(Favoris.this, HistoryActivity.class));
    }

    private void checkEmptyState() {
        // En supposant que 'favoriteCars' contient la liste actuelle des voitures favorites
        if (favoriteCars.isEmpty()) {
            // Liste vide : afficher le message et l'image
            rvFavorites.setVisibility(View.GONE);
            ivEmptyImage.setVisibility(View.VISIBLE);
        } else {
            // Liste non vide : afficher la liste et masquer le message/image
            rvFavorites.setVisibility(View.VISIBLE);
            ivEmptyImage.setVisibility(View.GONE);

            // N'oubliez pas d'initialiser et de définir l'adaptateur ici
            // Example: FavoritesAdapter adapter = new FavoritesAdapter(favoriteCars);
            // rvFavorites.setAdapter(adapter);
        }
    }



}

