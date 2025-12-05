package com.example.drive_2_go.data.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

//import com.example.drive_2_go.data.model.DashboardRow;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//on creer la classe qui va gérer l'acces a firebase
public class DashboardRepository {
    //la connexion a la base de donnée
    private final FirebaseFirestore db;

    //constucteur

    public DashboardRepository(){
        db = FirebaseFirestore.getInstance();
    }

    //LiveData pour les réservations par mois
    public LiveData<Map<Integer, Integer>> getReservationParMois(){
        //La Map associe mois -> nombre_de_reservations (par exemple 1 → 12 pour janvier = 12 réservations).
        MutableLiveData<Map<Integer, Integer>> liveData = new MutableLiveData<>();

        //Effectue une requête Firestore pour lire tous les documents de la collection "reservations".
        db.collection("Reservation")
                .get()
                //Ajoute un callback exécuté quand la requête réussit.
                //
                //queryDocumentSnapshots contient tous les documents retournés par
                // Firestore (méta + champs des docs).
                .addOnSuccessListener(queryDocumentSnapshots -> {

                    // Affiche chaque document pour debug
                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                        Log.d("FIREBASE_DOC", "Document ID: " + doc.getId() + " -> Data: " + doc.getData());
                    }


                    //Crée une nouvelle HashMap pour stocker le compteur des réservations par mois.
                    Map<Integer, Integer> reservationsParMois = new HashMap<>();

                    //Initialise la Map avec les 12 mois (1 à 12) et zéro pour chaque mois.
                    for(int i = 1;i <= 12 ; i++) reservationsParMois.put(i,0);

                    //Boucle sur chaque document retourné par la requête Firestore.
                    for(QueryDocumentSnapshot doc : queryDocumentSnapshots){

                        //on recupére la date de début de la reservation
                        Timestamp timestamp = doc.getTimestamp(("date_debut"));

                        // convetir Timestamp -> Calender
                        if (timestamp != null) {
                            Calendar cal = Calendar.getInstance();
                            //extraire les mois
                            cal.setTime(timestamp.toDate());
                            //MONTH commence a 0 on ajoute 1
                            int mois = cal.get(Calendar.MONTH) + 1;



                            //Incrementation du compteur
                            reservationsParMois.put(
                                    mois,
                                    reservationsParMois.get(mois) + 1
                            );
                        }else{
                            Log.d("FIREBASE_DOC", "Le champ date_debut est null pour le doc: " + doc.getId());



                        }
                    }
                    liveData.setValue(reservationsParMois);
                })
                .addOnFailureListener(e -> {
                    Log.e("FIREBASE_ERROR", "Erreur récupération des reservations", e);
                    liveData.setValue(new HashMap<>());
                });

        return liveData;
    }

}
