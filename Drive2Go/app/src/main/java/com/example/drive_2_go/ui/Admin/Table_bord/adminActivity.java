package com.example.drive_2_go.ui.Admin.Table_bord;

import android.os.Bundle;

// 1. IMPORT IMPORTANT : Ta classe de base
import com.example.drive_2_go.ui.Admin.ComposantCommunAdmin.BaseAdminActivity;
import com.example.drive_2_go.R;
<<<<<<< HEAD

public class adminActivity extends AppCompatActivity {
=======
import com.example.drive_2_go.ui.Admin.Gestion_Reservations.ReservationsActivity;

// 2. CHANGEMENT ICI : On hérite de BaseAdminActivity
public class adminActivity extends BaseAdminActivity {

    //private Button btnreservation;
    // On supprime tous les ImageButton ici, ils sont gérés par BaseAdminActivity !
>>>>>>> 5d37a57c54970dd9dd71f1ac3924cdaf257ecc1d

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // EdgeToEdge.enable(this); // Tu peux le garder si tu l'utilises
        setContentView(R.layout.activity_admin);

<<<<<<< HEAD
=======
        // 3. LA LIGNE MAGIQUE : Active la barre de navigation et la gestion des couleurs
        setupNavigation();

        // --- Logique spécifique à CETTE page seulement ---

        // Ce bouton est au milieu de ton écran (pas dans la barre), donc on le garde ici

>>>>>>> 5d37a57c54970dd9dd71f1ac3924cdaf257ecc1d
    }


}