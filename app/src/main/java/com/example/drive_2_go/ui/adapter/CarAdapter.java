package com.example.drive_2_go.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.drive_2_go.R;
import com.example.drive_2_go.data.model.Car;
import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {

    private List<Car> carList;

    public CarAdapter(List<Car> carList) {
        this.carList = carList;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_car, parent, false);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        Car car = carList.get(position);
        holder.brand.setText(car.getBrand());
        holder.model.setText(car.getModel());
        holder.price.setText(String.valueOf(car.getPrice()));
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    static class CarViewHolder extends RecyclerView.ViewHolder {
        TextView brand, model, price;
        public CarViewHolder(@NonNull View itemView) {
            super(itemView);
            brand = itemView.findViewById(R.id.carBrand);
            model = itemView.findViewById(R.id.carModel);
            price = itemView.findViewById(R.id.carPrice);
        }
    }
}
