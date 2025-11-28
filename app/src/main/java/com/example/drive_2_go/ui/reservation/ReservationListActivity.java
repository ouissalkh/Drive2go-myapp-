package com.example.drive_2_go.ui.reservation;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drive_2_go.R;
import com.example.drive_2_go.ui.adapter.ReservationAdapter;
import com.example.drive_2_go.viewmodel.ReservationViewModel;

public class ReservationListActivity extends AppCompatActivity {

    private ReservationViewModel viewModel;
    private ReservationAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_list);

        RecyclerView recyclerView = findViewById(R.id.recyclerReservations);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ReservationAdapter(new java.util.ArrayList<>());
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(ReservationViewModel.class);

        viewModel.getReservations().observe(this, reservations -> {
            adapter.updateList(reservations);
        });
    }
}
