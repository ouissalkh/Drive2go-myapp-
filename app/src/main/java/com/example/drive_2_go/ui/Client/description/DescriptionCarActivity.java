package com.example.drive_2_go.ui.Client.description;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

import com.example.drive_2_go.R;
import com.example.drive_2_go.data.model.Car;
import com.example.drive_2_go.ui.Client.accueil.AccueilActivity;

public class DescriptionCarActivity extends AppCompatActivity {
    private ImageButton backButton;
    private ImageButton favBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.description_car); // Votre layout de description

        // 1. Initialisation du bouton de retour (Assurez-vous que cet ID existe dans description_car.xml)
        backButton = findViewById(R.id.backbtn);
        ImageButton favBtn = findViewById(R.id.favbtn);

        backButton.setOnClickListener(v -> {
            backToHome();
        });

        // Récupérer l'objet Car de l'Intent
        Car selectedCar = (Car) getIntent().getSerializableExtra("selected_car");
        // Au départ -> afficher icône selon favoris
        updateFavIcon(favBtn, selectedCar.isFavorite());
        // Quand on clique sur bouton favoris
        favBtn.setOnClickListener(v -> {
            boolean newState = !selectedCar.isFavorite();
            selectedCar.setFavorite(newState);

            // Changer l’icône
            updateFavIcon(favBtn, newState);

            // Sauvegarder dans favoris
            saveFavoriteState(selectedCar);
        });

        if (selectedCar != null) {
            // Vous pouvez maintenant utiliser selectedCar pour afficher les détails
            // Exemple :
            // TextView tvName = findViewById(R.id.tv_car_name);
            // tvName.setText(selectedCar.getName());
        }
    }
    private void backToHome(){
        startActivity(new Intent(DescriptionCarActivity.this, AccueilActivity.class));
    }

    private void updateFavIcon(ImageButton favBtn, boolean isFav) {
        if (isFav) {
            favBtn.setColorFilter(getResources().getColor(android.R.color.holo_orange_light));
        } else {
            favBtn.setColorFilter(getResources().getColor(android.R.color.white));
        }
    }
    private void saveFavoriteState(Car car) {
        getSharedPreferences("favorites", MODE_PRIVATE)
                .edit()
                .putBoolean(car.getId() + "_fav", car.isFavorite())
                .apply();
    }

}