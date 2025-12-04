package com.example.drive_2_go.ui.Admin.Gestion_Reservations;

import android.os.Bundle;
<<<<<<< HEAD

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
=======
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

>>>>>>> 5d37a57c54970dd9dd71f1ac3924cdaf257ecc1d
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.drive_2_go.R;
<<<<<<< HEAD
=======
import com.example.drive_2_go.ui.Admin.ComposantCommunAdmin.BaseAdminActivity;

public class ReservationsActivity extends BaseAdminActivity {
>>>>>>> 5d37a57c54970dd9dd71f1ac3924cdaf257ecc1d


<<<<<<< HEAD
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reservations);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
=======
    private TextView tvTotalCount;
    private AutoCompleteTextView autoCompleteTextView;
    private ArrayAdapter<String> adapterItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 1. Charger le layout
        try {
            WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
            setContentView(R.layout.activity_reservations);
        } catch (Exception e) {
            Log.e("DEBUG_ERROR", "Erreur lors du setContentView: " + e.getMessage());
            Toast.makeText(this, "Erreur XML critique", Toast.LENGTH_LONG).show();
            return; // On arrête tout si le XML est cassé
        }

        // 2. Navigation (Sécurisée)
        try {
            setupNavigation();
        } catch (Exception e) {
            Log.e("DEBUG_ERROR", "Erreur dans setupNavigation: " + e.getMessage());
            // On ne fait rien, comme ça l'appli ne plante pas, juste la barre qui ne marche pas
        }

        // 3. Gestion des marges système (Sécurisée)
        View drawerLayout = findViewById(R.id.drawerLayout);
        if (drawerLayout != null) {
            ViewCompat.setOnApplyWindowInsetsListener(drawerLayout, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        } else {
            Log.e("DEBUG_ERROR", "Impossible de trouver R.id.drawerLayout");
        }

        // 4. Initialisation des vues (Sécurisée)
        tvTotalCount = findViewById(R.id.tv_total_count);
        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);

        // 5. Configuration Dropdown
        if (autoCompleteTextView != null) {
            String[] statusItems = {"Tous les statuts", "Confirmée", "En attente", "Annulée", "Terminée"};
            adapterItems = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, statusItems);
            autoCompleteTextView.setAdapter(adapterItems);

            autoCompleteTextView.setOnItemClickListener((parent, view, position, id) -> {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(ReservationsActivity.this, "Filtre : " + item, Toast.LENGTH_SHORT).show();
            });
        }




>>>>>>> 5d37a57c54970dd9dd71f1ac3924cdaf257ecc1d
    }
}