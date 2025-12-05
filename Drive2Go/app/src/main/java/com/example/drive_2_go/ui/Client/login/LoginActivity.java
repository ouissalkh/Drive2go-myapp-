package com.example.drive_2_go.ui.Client.login;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.drive_2_go.R;
import com.example.drive_2_go.ui.Admin.Table_bord.HomeActivityAdmin;
import com.example.drive_2_go.ui.Client.accueil.AccueilActivity;
import com.example.drive_2_go.ui.Client.creationCompte.RegisterActivity;
import com.example.drive_2_go.ui.Client.creationCompte.VerifyCodeActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText etEmail, etPassword;
    private Button btnLogin, btnRegister;
    private FirebaseAuth auth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        etEmail = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.tv_register);

        btnRegister.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));

        btnLogin.setOnClickListener(v -> loginUser());
    }

    private void loginUser() {
        String email = etEmail.getText().toString().trim();
        String pass = etPassword.getText().toString().trim();

        if (email.isEmpty() || pass.isEmpty()) {
            Toast.makeText(this, "Remplissez tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.signInWithEmailAndPassword(email, pass)
                .addOnSuccessListener(authResult -> {
                    String uid = authResult.getUser().getUid();

                    db.collection("users").document(uid).get()
                            .addOnSuccessListener(doc -> {
                                if (!doc.exists()) {
                                    Toast.makeText(this, "Profil introuvable", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                Boolean verified = doc.getBoolean("account_verified");
                                String role = doc.getString("role");
                                if (verified == null || !verified) {
                                    Toast.makeText(this, "Veuillez vérifier votre compte avant de vous connecter", Toast.LENGTH_LONG).show();
                                    // rediriger vers VerifyCodeActivity si besoin
                                    Intent i = new Intent(LoginActivity.this, VerifyCodeActivity.class);
                                    i.putExtra("userId", uid);
                                    startActivity(i);
                                    return;
                                }

                                if ("admin".equals(role)) {
                                    startActivity(new Intent(LoginActivity.this, HomeActivityAdmin.class));
                                    finish();
                                } else {
                                    startActivity(new Intent(LoginActivity.this, AccueilActivity.class));
                                    finish();
                                }
                            })
                            .addOnFailureListener(e -> Toast.makeText(this, "Erreur Firestore: " + e.getMessage(), Toast.LENGTH_LONG).show());
                })
                .addOnFailureListener(e -> Toast.makeText(this, "Erreur Auth: " + e.getMessage(), Toast.LENGTH_LONG).show());
    }
}
