package com.example.drive_2_go.ui.Admin.Table_bord;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class DashboardRepository {

    // Exemple – remplacer par SQLite ensuite
    public List<Integer> getMonthlyRentCount() {
        return Arrays.asList(5, 10, 7, 12, 8, 15);
    }

    public List<Integer> getCarTypesCount() {
        return Arrays.asList(20, 10, 5);
    }

    public List<DashboardRow> getLastOperations() {
        List<DashboardRow> list = new ArrayList<>();
        list.add(new DashboardRow("Location Renault", "12/11/2025"));
        list.add(new DashboardRow("Location BMW", "15/11/2025"));
        list.add(new DashboardRow("Location Audi", "18/11/2025"));
        return list;
    }
}
