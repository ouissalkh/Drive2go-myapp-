package com.example.drive_2_go.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.drive_2_go.R;
import com.example.drive_2_go.ui.Admin.Table_bord.adminActivity;
import com.example.drive_2_go.ui.Client.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnStart;
    private Button bouttonAdmin ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getstarted);

        btnStart = findViewById(R.id.btn_start);

        btnStart.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
        bouttonAdmin = findViewById(R.id.btnadmin);

        bouttonAdmin.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, adminActivity.class);
            startActivity(intent);
            finish();
        });
    }
}

