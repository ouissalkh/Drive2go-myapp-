package com.example.drive_2_go.ui.Admin.Gestion_Reservations;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.drive_2_go.R;
import com.example.drive_2_go.viewmodel.ReservationViewModel;

public class ReservationsActivity extends AppCompatActivity {

    private ReservationViewModel viewModel;
    private TextView tvTotalCount;

    // --- AJOUT POUR LE DROPDOWN ---
    private AutoCompleteTextView autoCompleteTextView;
    private ArrayAdapter<String> adapterItems;
    // ------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        setContentView(R.layout.activity_reservations);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawerLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 1. Initialiser les vues existantes
        tvTotalCount = findViewById(R.id.tv_total_count);

        // ---------------------------------------------------------
        // 2. CONFIGURATION DU MENU DÉROULANT (DROPDOWN)
        // ---------------------------------------------------------

        // A. Récupérer la vue
        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);

        // B. Définir les options
        String[] statusItems = {"Tous les statuts", "Confirmée", "En attente", "Annulée", "Terminée"};

        // C. Créer l'adapter (pont entre la liste et la vue)
        adapterItems = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, statusItems);

        // D. Assigner l'adapter
        autoCompleteTextView.setAdapter(adapterItems);

        // E. Gérer le clic sur une option
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemSelectionne = parent.getItemAtPosition(position).toString();

                // Afficher un message temporaire (Test)
                Toast.makeText(ReservationsActivity.this, "Filtre : " + itemSelectionne, Toast.LENGTH_SHORT).show();

                // ICI : Tu pourras appeler ton ViewModel pour filtrer la liste plus tard
                // exemple: viewModel.filterReservations(itemSelectionne);
            }
        });
        // ---------------------------------------------------------


        // 3. Initialiser le ViewModel
        viewModel = new ViewModelProvider(this).get(ReservationViewModel.class);

        // 4. Observer les données
        viewModel.getReservations().observe(this, reservations -> {
            if (reservations != null) {
                int total = reservations.size();
                tvTotalCount.setText(String.valueOf(total));
            } else {
                tvTotalCount.setText("0");
            }
        });
    }
}