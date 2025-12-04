package com.example.drive_2_go.ui.Admin.Gestion_Users;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.drive_2_go.R;
import com.example.drive_2_go.ui.Admin.ComposantCommunAdmin.BaseAdminActivity;
import com.example.drive_2_go.ui.Admin.Gestion_Reservations.ReservationsActivity;
import com.example.drive_2_go.ui.Admin.Table_bord.adminActivity;
import com.example.drive_2_go.ui.Client.favoris.Favoris;
import com.example.drive_2_go.ui.Client.history.HistoryActivity;

public class UsersGestionActivity extends BaseAdminActivity {

    private AutoCompleteTextView autoCompleteRole;
    private AutoCompleteTextView autoCompleteStatus;

    private ArrayAdapter<String> adapterRole;
    private ArrayAdapter<String> adapterStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // EdgeToEdge.enable(this); // Garde-le si tu l'utilises
        setContentView(R.layout.activity_users_gestion);


        // C'est elle qui va colorer le bouton "Users" automatiquement en vert
        // et rendre les autres boutons (Home, Parc, Resa) cliquables.
        setupNavigation();

        // 2. CONFIGURATION DU MENU DÉROULANT "RÔLES"
        autoCompleteRole = findViewById(R.id.autoCompleteTxtRole);

        // La liste des choix
        String[] roles = {"Tous les rôles", "Client", "Admin"};

        // Création de l'adaptateur
        adapterRole = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, roles);
        autoCompleteRole.setAdapter(adapterRole);

        // Action quand on clique sur un élément
        autoCompleteRole.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String roleSelectionne = parent.getItemAtPosition(position).toString();

                // TEST : Affiche ce qu'on a choisi
                Toast.makeText(UsersGestionActivity.this, "Filtre choisi : " + roleSelectionne, Toast.LENGTH_SHORT).show();

                // PLUS TARD : Ici tu appelleras ta fonction pour filtrer ta liste d'utilisateurs
                // exemple: filtrerListeParRole(roleSelectionne);
            }
        });

// 3. CONFIGURATION DU MENU DÉROULANT "STATUT" (Le 2ème)
        // -----------------------------------------------------------
        autoCompleteStatus = findViewById(R.id.autoCompleteTxtStatus);

        String[] status = {"Tous les statuts", "Actif", "Bloqué"};

        adapterStatus = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, status);
        autoCompleteStatus.setAdapter(adapterStatus);

        autoCompleteStatus.setOnItemClickListener((parent, view, position, id) -> {
            String statusSelectionne = parent.getItemAtPosition(position).toString();
            Toast.makeText(UsersGestionActivity.this, "Statut : " + statusSelectionne, Toast.LENGTH_SHORT).show();
        });
    }
    }






