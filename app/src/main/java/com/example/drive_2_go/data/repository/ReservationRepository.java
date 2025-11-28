package com.example.drive_2_go.data.repository;

import androidx.lifecycle.MutableLiveData;
import com.example.drive_2_go.data.model.Reservation;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReservationRepository {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    // 1️⃣ Compter les réservations par mois pour le BarChart
    public void loadReservationsCountByMonth(MutableLiveData<Map<Integer, Integer>> liveData) {
        db.collection("Reservation")
                .get()
                .addOnSuccessListener(query -> {
                    Map<Integer, Integer> reservationsParMois = new HashMap<>();
                    for (DocumentSnapshot doc : query.getDocuments()) {
                        Reservation r = doc.toObject(Reservation.class);
                        if (r != null && r.getDateDebut() != null) {
                            int month = r.getDateDebut().toDate().getMonth() + 1; // getMonth() = 0-11
                            Integer current = reservationsParMois.get(month);
                            if (current == null) current = 0;
                            reservationsParMois.put(month, current + 1);
                        }

                    }
                    liveData.postValue(reservationsParMois);
                });
    }

    // 2️⃣ Charger toutes les réservations pour la liste complète
    public void loadReservations(MutableLiveData<ArrayList<Reservation>> liveData) {
        db.collection("reservations")
                .get()
                .addOnSuccessListener(query -> {
                    ArrayList<Reservation> list = new ArrayList<>();
                    for (DocumentSnapshot doc : query.getDocuments()) {
                        Reservation r = doc.toObject(Reservation.class);
                        if (r != null) list.add(r);
                    }
                    liveData.postValue(list);
                });
    }
}
