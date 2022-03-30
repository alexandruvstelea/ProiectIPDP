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

public class EmployedBarbersAdapter extends RecyclerView.Adapter<EmployedBarbersAdapter.EmployeesViewHolder> {

    private Context context;
    private List<User> employeesList;

    public EmployedBarbersAdapter(Context context) {
        this.context = context;
    }

    public void setEmployeesList(List<User> employeesList) {
        this.employeesList = employeesList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EmployedBarbersAdapter.EmployeesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_row_shop, parent, false);
        return new EmployeesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployedBarbersAdapter.EmployeesViewHolder holder, int position) {
        holder.name.setText(this.employeesList.get(position).getFirstName() + " " + this.employeesList.get(position).getLastName());
        holder.userName.setText(this.employeesList.get(position).getUsername());
        holder.viewDetailsButton.setText("Fire");
    }


    public class EmployeesViewHolder extends RecyclerView.ViewHolder {

        TextView name, userName;
        Button viewDetailsButton;

        public EmployeesViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.shopNameText);
            userName = view.findViewById(R.id.shopAddressText);
            viewDetailsButton = view.findViewById(R.id.detailsButton);
            viewDetailsButton.setOnClickListener(this::onClick);
        }

        public void onClick(View v) {
            if (v.getId() == viewDetailsButton.getId()) {
                UserDB userDB = UserDB.getDBInstance(context.getApplicationContext());
                userDB.userDAO().updateEmployerID(-1, userName.getText().toString());
                Toast.makeText(context, "Successfully fired " + name.getText().toString(), Toast.LENGTH_SHORT).show();
                removeItem(getAbsoluteAdapterPosition());
            }
        }

        private void removeItem(int position) {
            employeesList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, employeesList.size());
        }
    }

    @Override
    public int getItemCount() {
        return this.employeesList.size();
    }


}
