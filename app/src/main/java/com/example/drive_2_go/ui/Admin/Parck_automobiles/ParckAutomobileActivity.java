package com.example.drive_2_go.ui.Admin.Parck_automobiles;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.drive_2_go.R;
import com.example.drive_2_go.ui.Admin.ComposantCommunAdmin.BaseAdminActivity;

public class ParckAutomobileActivity extends BaseAdminActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // EdgeToEdge.enable(this); // Garde-le si tu l'utilises
        setContentView(R.layout.activity_parck_automobile);


        // C'est elle qui va colorer le bouton "Users" automatiquement en vert
        // et rendre les autres boutons (Home, Parc, Resa) cliquables.
        setupNavigation();
    }
}