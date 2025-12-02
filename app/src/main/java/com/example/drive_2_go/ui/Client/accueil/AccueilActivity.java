package com.example.drive_2_go.ui.Client.accueil;

import com.example.drive_2_go.R;
import com.example.drive_2_go.data.model.Car;
import com.example.drive_2_go.ui.Client.description.DescriptionCarActivity;
import com.example.drive_2_go.ui.Client.favoris.Favoris;
import com.example.drive_2_go.ui.Client.history.HistoryActivity;
import com.example.drive_2_go.ui.Client.profil.Profil;
import com.example.drive_2_go.ui.adapter.BrandAdapter;
import com.example.drive_2_go.ui.adapter.CarAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccueilActivity extends AppCompatActivity implements CarAdapter.OnCarClickListener{

    private RecyclerView rvBrands;
    private RecyclerView rvCarListings;
    private ImageButton buttonProfil;
    private ImageButton buttonHome;
    private ImageButton buttonFavoris;
    private ImageButton buttonHistory;
    private ImageButton btnMenu;
    private LinearLayout contactInfoPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        buttonProfil = findViewById(R.id.buttonProfil);
        buttonHome = findViewById(R.id.buttonHome);
        buttonFavoris = findViewById(R.id.buttonFavoris);
        buttonHistory = findViewById(R.id.buttonHistory);

        buttonProfil.setOnClickListener(v -> openProfil());
        buttonFavoris.setOnClickListener(v -> openFavoris());
        buttonHome.setOnClickListener(v -> Toast.makeText(this,"Déjà ici", Toast.LENGTH_SHORT).show());
        buttonHistory.setOnClickListener(v -> openHistory());

        // <<< LIAISON ET LOGIQUE DU NOUVEAU BOUTON/PANNEAU
        btnMenu = findViewById(R.id.btn_menu);
        contactInfoPanel = findViewById(R.id.contact_info_panel);

        btnMenu.setOnClickListener(v -> {
            if (contactInfoPanel.getVisibility() == View.GONE) {
                contactInfoPanel.setVisibility(View.VISIBLE);
            } else {
                contactInfoPanel.setVisibility(View.GONE);
            }
        });

        // RecyclerView des marques
        rvBrands = findViewById(R.id.rv_brands);
        List<Integer> icons = Arrays.asList(
                R.drawable.ic_audi,
                R.drawable.ic_bmw,
                R.drawable.ic_ford,
                R.drawable.ic_mercedes,
                R.drawable.ic_volkswagen
        );
        BrandAdapter brandAdapter = new BrandAdapter(icons, brandResId -> {
            Toast.makeText(AccueilActivity.this, "Brand clicked!", Toast.LENGTH_SHORT).show();
        });
        rvBrands.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvBrands.setAdapter(brandAdapter);

        // RecyclerView des voitures
        rvCarListings = findViewById(R.id.rv_car_listings);
        List<Car> availableCars = createCarData();
        CarAdapter carAdapter = new CarAdapter(availableCars, this);
        rvCarListings.setLayoutManager(new LinearLayoutManager(this));
        rvCarListings.setAdapter(carAdapter);
        selectButton(buttonHome); // si tu es dans Home
    }
    @Override
    public void onCarClick(Car car) {
        // Créer un Intent pour démarrer l'activité de description
        Intent intent = new Intent(AccueilActivity.this, DescriptionCarActivity.class); // <<< Remplacez DescriptionCarActivity.class par le nom réel de votre activité de description

        // Passer l'objet Car sérialisé à la nouvelle activité
        intent.putExtra("selected_car", car);

        // Démarrer l'activité
        startActivity(intent);

        Toast.makeText(this, "Voiture cliquée: " + car.getName(), Toast.LENGTH_SHORT).show();
    }
    private void selectButton(ImageButton button) {
        button.setSelected(true);
    }

    private void openProfil(){
        startActivity(new Intent(AccueilActivity.this, Profil.class));
    }

    private void openFavoris(){
        startActivity(new Intent(AccueilActivity.this, Favoris.class));
    }
    private void openHistory(){
        startActivity(new Intent(AccueilActivity.this, HistoryActivity.class));
    }

    private List<Car> createCarData() {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car(
                1,
                "Renault Captur",
                "GH-391-AD",
                "450 Dh",
                R.drawable.img_renault_captur,
                "essence",
                "max 800 km",
                2,
                true,
                "M",
                5,
                5,
                true,
                false
        ));
        return cars;
    }
}
