package com.example.drive_2_go.ui.start;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.drive_2_go.R;

public class GetStartedActivity extends AppCompatActivity {

    private Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getstarted);

        btnStart = findViewById(R.id.btn_start);

        btnStart.setOnClickListener(v -> {
            Intent intent = new Intent(GetStartedActivity.this, com.example.drive_2_go.ui.login.LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
