package com.example.barbersapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AppointmentsListAdapter extends RecyclerView.Adapter<AppointmentsListAdapter.AppointmentsViewHolder>{

    private Context context;
    private List<Appointment> appointmentsList;

    public AppointmentsListAdapter(Context context) {
        this.context = context;
    }

    public void setAppointmentsList(List<Appointment> appointmentsList) {
        this.appointmentsList = appointmentsList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AppointmentsListAdapter.AppointmentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_row_shop, parent, false);
        return new AppointmentsListAdapter.AppointmentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentsListAdapter.AppointmentsViewHolder holder, int position) {
        holder.hourDate.setText(this.appointmentsList.get(position).getHour() + " " + this.appointmentsList.get(position).getDate());
        holder.barberShop.setText(this.appointmentsList.get(position).getBarberFirstName() + " " + this.appointmentsList.get(position).getShopName());
        holder.cancelButton.setText("Cancel");
    }


    public class AppointmentsViewHolder extends RecyclerView.ViewHolder {

        TextView hourDate, barberShop;
        Button cancelButton;

        public AppointmentsViewHolder(View view) {
            super(view);
            hourDate = view.findViewById(R.id.shopNameText);
            barberShop = view.findViewById(R.id.shopAddressText);
            cancelButton = view.findViewById(R.id.detailsButton);
            cancelButton.setOnClickListener(this::onClick);
        }

        public void onClick(View v) {
            if (v.getId() == cancelButton.getId()) {
                String[] hD = hourDate.getText().toString().split(" ");
                String[] bS = barberShop.getText().toString().split(" ");
                AppointmentsDB appointmentsDB = AppointmentsDB.getDBInstance(context.getApplicationContext());
                appointmentsDB.appointmentsDAO().updateStatus(false,bS[0],hD[1],Integer.parseInt(hD[0]));
                Toast.makeText(context, "Appointment canceled", Toast.LENGTH_SHORT).show();
                removeItem(getAbsoluteAdapterPosition());
            }
        }

        private void removeItem(int position) {
            appointmentsList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, appointmentsList.size());
        }
    }

    @Override
    public int getItemCount() {
        return this.appointmentsList.size();
    }

}
