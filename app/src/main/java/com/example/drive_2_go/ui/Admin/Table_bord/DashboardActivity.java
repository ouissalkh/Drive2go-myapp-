package com.example.drive_2_go.ui.Admin.Table_bord;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.drive_2_go.R;
import com.example.drive_2_go.data.model.ReservationMonthCount;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    private BarChart barChart;
    private DashboardViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin); // Ton fichier XML

        barChart = findViewById(R.id.barChart);

        // Initialiser le ViewModel
        viewModel = new ViewModelProvider(this).get(DashboardViewModel.class);

        // Observer les données
        viewModel.getMonthlyReservations().observe(this, this::updateBarChart);
    }

    // Met à jour le BarChart lorsque les données changent
    private void updateBarChart(List<ReservationMonthCount> reservations) {
        if (reservations == null || reservations.isEmpty()) return;

        ArrayList<BarEntry> entries = new ArrayList<>();
        for (ReservationMonthCount r : reservations) {
            int monthIndex = Integer.parseInt(r.month) - 1; // 0 = Janvier
            entries.add(new BarEntry(monthIndex, r.total));
        }

        BarDataSet dataSet = new BarDataSet(entries, "Locations par mois");
        dataSet.setColor(Color.parseColor("#4CAF50"));
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueTextSize(12f);

        BarData data = new BarData(dataSet);
        barChart.setData(data);

        // Configurer le chart
        barChart.getDescription().setEnabled(false);
        barChart.animateY(1000);
        barChart.invalidate();
    }
}
