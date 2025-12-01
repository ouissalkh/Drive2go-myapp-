package com.example.drive_2_go.ui.Client.ptofil;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.drive_2_go.R;
import com.example.drive_2_go.ui.Client.accueil.AccueilActivity;
import com.example.drive_2_go.ui.Client.favoris.Favoris;
import com.google.android.material.imageview.ShapeableImageView;

public class Profil extends AppCompatActivity {

    private ShapeableImageView profileImage;
    private ImageButton buttonCamera;
    private Uri imageUri;
    private ImageButton buttonFavoris;
    private ImageButton buttonHome;
    private ImageButton buttonHistory;
    private ImageButton buttonProfil;

    // Lanceur pour ouvrir la galerie et récupérer l'image
    private final ActivityResultLauncher<Intent> pickImageLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Uri selectedImageUri = result.getData().getData();
                    if (selectedImageUri != null) {
                        imageUri = selectedImageUri;
                        profileImage.setImageURI(imageUri);
                    }
                } else {
                    Toast.makeText(this, "Aucune image sélectionnée", Toast.LENGTH_SHORT).show();
                }
            });


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil); // remplace par ton vrai nom XML si différent

        profileImage = findViewById(R.id.profileImage);
        buttonCamera = findViewById(R.id.buttonCamera);
        buttonFavoris = findViewById(R.id.buttonFavoris);
        buttonHome = findViewById(R.id.buttonHome);
        buttonHistory = findViewById(R.id.buttonHistory);
        buttonProfil = findViewById(R.id.buttonProfil);



        buttonCamera.setOnClickListener(v -> openGallery());
        buttonFavoris.setOnClickListener(v -> openFavoris());
        buttonHome.setOnClickListener(v -> openAccueil());
        buttonProfil.setOnClickListener(v -> Toast.makeText(this,"Déjà ici", Toast.LENGTH_SHORT).show());
        selectButton(buttonProfil);
    }

    private void selectButton(ImageButton button) {
        button.setSelected(true);
    }

    private void openFavoris(){
        Intent intent = new Intent(Profil.this, Favoris.class);
        startActivity(intent);
    }
    private void openAccueil(){
        Intent intent = new Intent(Profil.this, AccueilActivity.class);
        startActivity(intent);
    }
    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        pickImageLauncher.launch(intent);
    }


}
