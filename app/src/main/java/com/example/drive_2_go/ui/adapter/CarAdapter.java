package com.example.drive_2_go.ui.adapter;
import com.example.drive_2_go.R;
import com.example.drive_2_go.data.model.Car;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import android.view.View;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {

    private List<Car> cars;
    private final OnCarClickListener listener;
    // 2. Définissez l'interface
    public interface OnCarClickListener {
        void onCarClick(Car car);
    }

    public CarAdapter(List<Car> cars, OnCarClickListener listener) {
        this.cars = cars;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cartecar, parent, false);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        Car car = cars.get(position);
        holder.tvCarName.setText(car.getName());
        holder.tvLicensePlate.setText(car.getLicensePlate());
        holder.tvPrice.setText(car.getPrice());
        holder.imgCar.setImageResource(car.getImageResId());
        holder.tvFuelType.setText(car.getFuelType());
        holder.tvMaxKm.setText(car.getMaxKm());
        holder.tvBaggageCount.setText(String.valueOf(car.getBaggageCount()));
        holder.tvGearType.setText(car.getGearType());
        holder.tvDoorCount.setText(String.valueOf(car.getDoorCount()));
        holder.tvPeopleCount.setText(String.valueOf(car.getPeopleCount()));
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onCarClick(car);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }

    static class CarViewHolder extends RecyclerView.ViewHolder {
        TextView tvCarName, tvLicensePlate, tvPrice, tvFuelType, tvMaxKm, tvBaggageCount, tvGearType, tvDoorCount, tvPeopleCount;
        ImageView imgCar;

        public CarViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCarName = itemView.findViewById(R.id.tv_car_name);
            tvLicensePlate = itemView.findViewById(R.id.tv_license_plate);
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvFuelType = itemView.findViewById(R.id.tv_fuel_type);
            tvMaxKm = itemView.findViewById(R.id.tv_max_km);
            tvBaggageCount = itemView.findViewById(R.id.tv_baggage_count);
            tvGearType = itemView.findViewById(R.id.tv_gear_type);
            tvDoorCount = itemView.findViewById(R.id.tv_door_count);
            tvPeopleCount = itemView.findViewById(R.id.tv_people_count);
            imgCar = itemView.findViewById(R.id.img_car);
        }
    }
}
