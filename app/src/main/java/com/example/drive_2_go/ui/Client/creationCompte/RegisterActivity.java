package com.example.drive_2_go.ui.Client.creationCompte;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RegisterActivity extends AppCompatActivity {

    private EditText etNom, etPrenom, etTel, etEmail, etPassword, etConfirm;
    private Button btnCreate;

    private FirebaseAuth auth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_compte);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        etNom = findViewById(R.id.inputNom);
        etPrenom = findViewById(R.id.inputPrenom);
        etTel = findViewById(R.id.inputTelephone);
        etEmail = findViewById(R.id.inputEmail);
        etPassword = findViewById(R.id.inputPassword);
        etConfirm = findViewById(R.id.inputConfirmPassword);
        btnCreate = findViewById(R.id.btnCreateAccount);

        btnCreate.setOnClickListener(v -> registerUser());
    }

    private void registerUser() {
        String nom = etNom.getText().toString().trim();
        String prenom = etPrenom.getText().toString().trim();
        String tel = etTel.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String pass = etPassword.getText().toString().trim();
        String confirm = etConfirm.getText().toString().trim();

        if (nom.isEmpty() || prenom.isEmpty() || tel.isEmpty() || email.isEmpty()
                || pass.isEmpty() || confirm.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!pass.equals(confirm)) {
            Toast.makeText(this, "Les mots de passe ne correspondent pas", Toast.LENGTH_SHORT).show();
            return;
        }

        // Crée l'utilisateur Firebase Auth
        auth.createUserWithEmailAndPassword(email, pass)
                .addOnSuccessListener(authResult -> {
                    String uid = authResult.getUser().getUid();

                    // Génère un code 6 chiffres
                    int code = new Random().nextInt(900000) + 100000;

                    Map<String, Object> userMap = new HashMap<>();
                    userMap.put("nom", nom);
                    userMap.put("prenom", prenom);
                    userMap.put("tel", tel);
                    userMap.put("email", email);
                    userMap.put("role", "client"); // role client par défaut
                    userMap.put("account_verified", false);
                    userMap.put("verification_code", code);
                    userMap.put("createdAt", System.currentTimeMillis());

                    db.collection("users").document(uid)
                            .set(userMap)
                            .addOnSuccessListener(aVoid -> {
                                // Envoi du mail dans un thread séparé (décommente si SMTP configuré)
                                new Thread(() -> {
                                    try {
                                        String subject = "Code de vérification Drive2Go";
                                        String body = "Bonjour " + nom + ",\n\nVotre code de vérification Drive2Go est : " + code + "\n\nMerci.";
                                        // EmailSender.sendEmail(email, subject, body); // décommente si SMTP prêt
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }).start();

                                // Pour tests: afficher le code (supprime en prod)
                                Toast.makeText(RegisterActivity.this, "Code (test): " + code, Toast.LENGTH_LONG).show();

                                // Lancer écran de vérification
                                Intent intent = new Intent(RegisterActivity.this, VerifyCodeActivity.class);
                                intent.putExtra("userId", uid);
                                startActivity(intent);
                                finish();
                            })
                            .addOnFailureListener(e ->
                                    Toast.makeText(RegisterActivity.this, "Erreur Firestore: " + e.getMessage(), Toast.LENGTH_LONG).show()
                            );
                })
                .addOnFailureListener(e ->
                        Toast.makeText(RegisterActivity.this, "Erreur Auth: " + e.getMessage(), Toast.LENGTH_LONG).show()
                );
    }
}
