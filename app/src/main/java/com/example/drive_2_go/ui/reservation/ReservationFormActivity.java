package com.example.drive_2_go.ui.reservation;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.drive_2_go.R;
import com.example.drive_2_go.ui.Client.accueil.AccueilActivity;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ReservationFormActivity extends AppCompatActivity {

    private static final String TAG = "ReservationForm";

    // UI Elements
    private TextView tvCarModel, tvStartDate, tvEndDate, tvTotalPrice;
    private Button btnConfirm;
    private ImageButton btnSelectStartDate, btnSelectEndDate;
    private RadioGroup rgPaymentMethod;
    private EditText etName, etPhone;

    // Firebase Instance
    private FirebaseFirestore db;

    // Constantes et SimuLation (À remplacer par la logique réelle de session)
    private static final int CAR_DAILY_PRICE = 200; // Prix de la voiture par défaut (DH/jr)
    private String currentCarId = "CAR_MERCEDES_A"; // ID de la voiture sélectionnée
    private String currentUserId = "USER_001"; // ID de l'utilisateur connecté

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_form);

        // Initialisation de Firebase
        db = FirebaseFirestore.getInstance();

        // 1. Initialisation des Vues (liées au fichier XML)
        tvCarModel = findViewById(R.id.tv_car_model);
        tvStartDate = findViewById(R.id.tv_start_date);
        tvEndDate = findViewById(R.id.tv_end_date);
        tvTotalPrice = findViewById(R.id.tv_total_price);
        btnConfirm = findViewById(R.id.btn_confirm_reservation);
        btnSelectStartDate = findViewById(R.id.btn_select_start_date);
        btnSelectEndDate = findViewById(R.id.btn_select_end_date);
        rgPaymentMethod = findViewById(R.id.rg_payment_method);
        etName = findViewById(R.id.et_client_name);
        etPhone = findViewById(R.id.et_client_phone);

        // Mise à jour initiale des textes
        tvCarModel.setText("Mercedes A-Class (" + CAR_DAILY_PRICE + " DH / jr)");
        tvTotalPrice.setText("Total : 0 DH");

        // 2. Charger les informations client depuis Firebase
        loadUserDataFromFirebase();

        // 3. Configuration des sélecteurs de date (MINI-CALENDRIER)
        // Lorsqu'on clique sur l'icône, on appelle la fonction d'affichage du calendrier
        btnSelectStartDate.setOnClickListener(v -> showDatePicker(tvStartDate));
        btnSelectEndDate.setOnClickListener(v -> showDatePicker(tvEndDate));

        // Écouteurs pour le calcul du prix automatique
        tvStartDate.addTextChangedListener(new PriceUpdateWatcher());
        tvEndDate.addTextChangedListener(new PriceUpdateWatcher());

        // 4. Gestion du bouton Confirmer
        btnConfirm.setOnClickListener(this::handleConfirmation);
    }

    /**
     * Ouvre le DatePickerDialog (mini-calendrier).
     */
    private void showDatePicker(TextView dateTextView) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // 1. Création du DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    // 2. Mise à jour du TextView avec la date sélectionnée
                    String date = String.format(Locale.getDefault(), "%02d/%02d/%d",
                            selectedDay, selectedMonth + 1, selectedYear);
                    dateTextView.setText(date);
                }, year, month, day);

        // Assurez-vous que l'utilisateur ne peut pas sélectionner de date passée
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

        datePickerDialog.show();
    }

    /**
     * Tente de charger le nom et le téléphone de l'utilisateur connecté depuis Firestore.
     */
    private void loadUserDataFromFirebase() {
        // En vrai, l'ID utilisateur doit venir de Firebase Auth (FirebaseAuth.getInstance().getCurrentUser().getUid())
        if (currentUserId.isEmpty()) {
            Toast.makeText(this, "Utilisateur non connecté.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Supposons une collection 'users'
        db.collection("users").document(currentUserId)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            String name = document.getString("name");
                            String phone = document.getString("phone");

                            // Mettre à jour les champs en lecture seule
                            if (name != null) etName.setText(name);
                            if (phone != null) etPhone.setText(phone);

                        } else {
                            Log.w(TAG, "Document utilisateur non trouvé. Utilisation des valeurs par défaut.");
                            etName.setText("Nom Prénom (Simulé)");
                            etPhone.setText("00 00 00 00 00");
                        }
                    } else {
                        Log.e(TAG, "Erreur de connexion Firestore : ", task.getException());
                        Toast.makeText(this, "Erreur de chargement des données client.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    /**
     * Calcule le prix total en fonction des dates de début et de fin.
     */
    private void calculateTotalPrice() {
        String startDateStr = tvStartDate.getText().toString();
        String endDateStr = tvEndDate.getText().toString();

        if (startDateStr.isEmpty() || endDateStr.isEmpty()) {
            tvTotalPrice.setText("Total : 0 DH");
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        try {
            Date startDate = sdf.parse(startDateStr);
            Date endDate = sdf.parse(endDateStr);

            if (startDate == null || endDate == null || endDate.before(startDate)) {
                tvTotalPrice.setText("Total : Erreur Date");
                return;
            }

            // Calcul de la différence en jours (+1 pour inclure le jour de fin)
            long diff = endDate.getTime() - startDate.getTime();
            int days = (int) (diff / (1000 * 60 * 60 * 24)) + 1;

            int totalPrice = days * CAR_DAILY_PRICE;
            tvTotalPrice.setText("Total : " + totalPrice + " DH");

        } catch (ParseException e) {
            tvTotalPrice.setText("Total : Erreur Format");
        }
    }

    /**
     * Enregistre la réservation dans Firebase et déclenche l'écoute Admin.
     */
    private void handleConfirmation(View view) {
        String startDate = tvStartDate.getText().toString();
        String endDate = tvEndDate.getText().toString();

        // Récupérer le prix total et s'assurer qu'il est valide
        String totalPriceStr = tvTotalPrice.getText().toString().replace("Total : ", "").replace(" DH", "");

        if (startDate.isEmpty() || endDate.isEmpty() || totalPriceStr.contains("Erreur") || currentUserId.isEmpty()) {
            Toast.makeText(this, "Veuillez vérifier les dates et l'état du formulaire.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Récupérer la méthode de paiement sélectionnée
        int selectedId = rgPaymentMethod.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedId);
        String paymentMethod = selectedRadioButton.getText().toString();

        // Création de l'objet de réservation pour Firestore
        Map<String, Object> reservation = new HashMap<>();
        reservation.put("userId", currentUserId);
        reservation.put("carId", currentCarId);
        reservation.put("userName", etName.getText().toString());
        reservation.put("startDate", startDate);
        reservation.put("endDate", endDate);
        reservation.put("totalPrice", Integer.parseInt(totalPriceStr));
        reservation.put("paymentMethod", paymentMethod);
        reservation.put("status", "En attente de validation"); // Statut initial
        reservation.put("timestamp", Timestamp.now()); // Date de la demande

        // Enregistrement dans la collection 'reservations'
        db.collection("reservations")
                .add(reservation)
                .addOnSuccessListener(documentReference -> {
                    Log.d(TAG, "Réservation enregistrée avec ID: " + documentReference.getId());

                    // Le simple fait d'ajouter ce document déclenche la notification côté Admin
                    // (s'ils ont un listener actif sur cette collection).

                    Toast.makeText(this, "Demande envoyée ! En attente de validation de l'Administrateur.", Toast.LENGTH_LONG).show();

                    // Redirection vers l'accueil ou l'historique
                    Intent intent = new Intent(ReservationFormActivity.this, AccueilActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                })
                .addOnFailureListener(e -> {
                    Log.e(TAG, "Erreur lors de l'enregistrement de la réservation", e);
                    Toast.makeText(this, "Erreur: Échec de la confirmation. Veuillez réessayer.", Toast.LENGTH_LONG).show();
                });
    }

    /**
     * TextWatcher pour déclencher le calcul du prix chaque fois que les dates sont mises à jour.
     */
    private class PriceUpdateWatcher implements TextWatcher {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
        public void afterTextChanged(Editable s) {
            calculateTotalPrice();
        }
    }
}
