package com.example.drive_2_go.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drive_2_go.R;

import java.util.List;

public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.BrandViewHolder> {

    private List<Integer> brands;
    private OnBrandClickListener listener;

    public interface OnBrandClickListener {
        void onBrandClick(int brandResId);
    }

    public BrandAdapter(List<Integer> brands, OnBrandClickListener listener) {
        this.brands = brands;
        this.listener = listener;
    }

    @NonNull
    @Override
    public BrandViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_brand, parent, false);
        return new BrandViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BrandViewHolder holder, int position) {
        int iconRes = brands.get(position);

        holder.imgBrand.setImageResource(iconRes);

        holder.imgBrand.setOnClickListener(v -> {
            if (listener != null) {
                listener.onBrandClick(iconRes);
            }
        });
    }

    @Override
    public int getItemCount() {
        return brands.size();
    }

    public static class BrandViewHolder extends RecyclerView.ViewHolder {

        ImageButton imgBrand;

        public BrandViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBrand = itemView.findViewById(R.id.img_brand);
        }
    }
}

