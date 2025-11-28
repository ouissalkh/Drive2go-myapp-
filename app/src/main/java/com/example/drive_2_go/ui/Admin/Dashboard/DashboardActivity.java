package com.example.drive_2_go.ui.Admin.Dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.drive_2_go.R;
import com.example.drive_2_go.data.model.Reservation;
import com.example.drive_2_go.viewmodel.DashboardViewModel;
import com.example.drive_2_go.viewmodel.ReservationViewModel;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DashboardActivity extends AppCompatActivity {

    private BarChart barChart;
    private ReservationViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        barChart = findViewById(R.id.barChart);
        viewModel = new ViewModelProvider(this).get(ReservationViewModel.class);

        // Observer les réservations par mois
        viewModel.getReservationsParMois().observe(this, map -> {
            showChart(map);
        });
        viewModel.getReservationsParMois().observe(this, map -> {
            if (map != null && !map.isEmpty()) {
                showChart(map);
            } else {
                Log.d("DashboardActivity", "Aucune réservation trouvée");
            }
        });

    }

    private void showChart(Map<Integer, Integer> reservationsParMois) {

        // 1️⃣ Créer les entrées du BarChart
        List<BarEntry> entries = new ArrayList<>();
        for (int month = 1; month <= 12; month++) {
            Integer countObj = reservationsParMois.get(month);
            int count = (countObj != null) ? countObj : 0;  // compatible API 23
            entries.add(new BarEntry(month, count));
        }

        // 2️⃣ Créer le DataSet
        BarDataSet dataSet = new BarDataSet(entries, "Réservations par mois");
        dataSet.setColor(Color.parseColor("#FF6200EE")); // couleur personnalisée

        // 3️⃣ Créer BarData
        BarData data = new BarData(dataSet);
        data.setBarWidth(0.9f);

        // 4️⃣ Configurer le BarChart
        barChart.setData(data);
        barChart.getDescription().setEnabled(false);
        barChart.setFitBars(true);
        barChart.animateY(1000);
        barChart.invalidate();

        // 5️⃣ Configurer l'axe X avec les noms des mois
        String[] months = {"", "Jan", "Feb", "Mar", "Apr", "May", "Jun",
                "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

        com.github.mikephil.charting.components.XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new com.github.mikephil.charting.formatter.IndexAxisValueFormatter(months));
        xAxis.setPosition(com.github.mikephil.charting.components.XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(12);
    }


}
