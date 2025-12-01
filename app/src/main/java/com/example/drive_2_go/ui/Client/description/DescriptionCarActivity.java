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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.description_car); // Votre layout de description

        // 1. Initialisation du bouton de retour (Assurez-vous que cet ID existe dans description_car.xml)
        backButton = findViewById(R.id.backbtn);

        backButton.setOnClickListener(v -> {
            backToHome();
        });

        // Récupérer l'objet Car de l'Intent
        Car selectedCar = (Car) getIntent().getSerializableExtra("selected_car");

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

}