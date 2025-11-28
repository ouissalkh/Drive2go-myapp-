package com.example.drive_2_go.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drive_2_go.R;
import com.example.drive_2_go.data.model.Reservation;

import java.util.List;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ResViewHolder> {

    private List<Reservation> reservations;

    public ReservationAdapter(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    @NonNull
    @Override
    public ResViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_reservation, parent, false);
        return new ResViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ResViewHolder holder, int position) {
        Reservation r = reservations.get(position);

        holder.tvClient.setText("Client : " + r.getUtilisateur_id());
        holder.tvCar.setText("Voiture : " + r.getVoiture_id());
        holder.tvDates.setText("Du " + r.getDateDebut() + " au " + r.getDateFin());
        holder.tvPrix.setText("Prix total : " + r.getPrix_total() + " MAD");
        holder.tvStatut.setText("Statut : " + r.getStatut());
    }

    @Override
    public int getItemCount() {
        return reservations.size();
    }

    public void updateList(List<Reservation> newList) {
        this.reservations = newList;
        notifyDataSetChanged();
    }

    static class ResViewHolder extends RecyclerView.ViewHolder {

        TextView tvClient, tvCar, tvDates, tvPrix, tvStatut;

        public ResViewHolder(@NonNull View itemView) {
            super(itemView);

            tvClient = itemView.findViewById(R.id.tvClientName);
            tvCar = itemView.findViewById(R.id.tvCarModel);
            tvDates = itemView.findViewById(R.id.tvDates);
            tvPrix = itemView.findViewById(R.id.tvPrix);
            tvStatut = itemView.findViewById(R.id.tvStatut);
        }
    }
}
