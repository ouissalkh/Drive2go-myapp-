package com.example.drive_2_go.ui.Client.favoris;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
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
import com.example.drive_2_go.ui.adapter.CarAdapter;

import java.util.ArrayList;
import java.util.List;

public class Favoris extends AppCompatActivity{

    private ImageButton buttonProfil;
    private ImageButton buttonHome;
    private ImageButton buttonFavoris;
    private ImageButton buttonHistory;
    RecyclerView rvFavorites;
    TextView tvEmpty;
    CarAdapter adapter;
    List<Car> allCars = new ArrayList<>();
    List<Car> favoriteCars = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoris);

        rvFavorites = findViewById(R.id.rv_favorites);
        tvEmpty = findViewById(R.id.tv_empty);

        rvFavorites.setLayoutManager(new LinearLayoutManager(this));

        adapter = new CarAdapter(favoriteCars, (CarAdapter.OnCarClickListener) this);
        rvFavorites.setAdapter(adapter);


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



}

