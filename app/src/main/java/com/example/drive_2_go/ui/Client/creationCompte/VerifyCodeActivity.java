package com.example.drive_2_go.ui.Client.creationCompte;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class VerifyCodeActivity extends AppCompatActivity {

    private EditText etCode;
    private Button btnVerify;
    private FirebaseFirestore db;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verify_code);

        etCode = findViewById(R.id.et_code);
        btnVerify = findViewById(R.id.btn_verify);

        db = FirebaseFirestore.getInstance();
        userId = getIntent().getStringExtra("userId");

        btnVerify.setOnClickListener(v -> checkCode());
    }

    private void checkCode() {
        String input = etCode.getText().toString().trim();
        if (input.isEmpty()) {
            Toast.makeText(this, "Entrez le code", Toast.LENGTH_SHORT).show();
            return;
        }

        db.collection("users").document(userId).get()
                .addOnSuccessListener((DocumentSnapshot doc) -> {
                    if (!doc.exists()) {
                        Toast.makeText(this, "Utilisateur introuvable", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Long codeLong = doc.getLong("verification_code");
                    Boolean alreadyVerified = doc.getBoolean("account_verified");
                    if (alreadyVerified != null && alreadyVerified) {
                        Toast.makeText(this, "Compte déjà vérifié", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(VerifyCodeActivity.this, com.example.locationvoiture.LoginActivity.class));
                        finish();
                        return;
                    }

                    String correct = codeLong != null ? String.valueOf(codeLong) : "";
                    if (correct.equals(input)) {
                        db.collection("users").document(userId)
                                .update("account_verified", true)
                                .addOnSuccessListener(aVoid -> {
                                    Toast.makeText(this, "Compte vérifié ✅", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(VerifyCodeActivity.this, com.example.locationvoiture.LoginActivity.class));
                                    finish();
                                })
                                .addOnFailureListener(e ->
                                        Toast.makeText(this, "Erreur: " + e.getMessage(), Toast.LENGTH_LONG).show()
                                );
                    } else {
                        Toast.makeText(this, "Code incorrect", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e ->
                        Toast.makeText(this, "Erreur Firestore: " + e.getMessage(), Toast.LENGTH_LONG).show()
                );
    }
}
