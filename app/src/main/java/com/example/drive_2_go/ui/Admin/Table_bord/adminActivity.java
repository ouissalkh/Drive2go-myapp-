package com.example.drive_2_go.ui.Admin.Table_bord;

import android.os.Bundle;

import com.example.drive_2_go.R;
import com.example.drive_2_go.ui.Admin.ComposantCommunAdmin.BaseAdminActivity;
import com.example.drive_2_go.ui.Admin.Gestion_Reservations.ReservationsActivity;

public class adminActivity extends BaseAdminActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Charger le layout
        setContentView(R.layout.activity_admin);

        // Activer la barre de navigation et la gestion des couleurs
        setupNavigation();

        // --- Logique spécifique à cette page ---
        // Par exemple, initialisation de boutons ou autres composants spécifiques
    }
}
