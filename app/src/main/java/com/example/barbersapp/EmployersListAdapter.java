package com.example.barbersapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class EmployersListAdapter extends RecyclerView.Adapter<EmployersListAdapter.EmployersViewHolder> {

    private Context context;
    private List<Shop> shopList;
    private String currentUserName;

    public EmployersListAdapter(Context context) {
        this.context = context;
    }

    public void setShopList(List<Shop> shopList) {
        this.shopList = shopList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EmployersListAdapter.EmployersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_row_shop, parent, false);
        return new EmployersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployersListAdapter.EmployersViewHolder holder, int position) {
        holder.shopName.setText(this.shopList.get(position).getShopName());
        holder.shopAddress.setText(this.shopList.get(position).getShopAddress());
        holder.viewDetailsButton.setText("Apply");
    }

    public class EmployersViewHolder extends RecyclerView.ViewHolder {

        TextView shopName, shopAddress;
        Button viewDetailsButton;

        public EmployersViewHolder(View view) {
            super(view);
            shopName = view.findViewById(R.id.shopNameText);
            shopAddress = view.findViewById(R.id.shopAddressText);
            viewDetailsButton = view.findViewById(R.id.detailsButton);
            viewDetailsButton.setOnClickListener(this::onClick);

        }

        public void onClick(View v) {
            if (v.getId() == viewDetailsButton.getId()) {
                UserDB userDB = UserDB.getDBInstance(context.getApplicationContext());
                ShopDB shopDB = ShopDB.getDBInstance(context.getApplicationContext());
                int shopID = shopDB.shopDAO().getShopID(shopName.getText().toString());
                userDB.userDAO().updateEmployerID(shopID,currentUserName);
                Toast.makeText(context, "Successfully applied to "+ shopName.getText().toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, HomePageActivityBarber.class);
                intent.putExtra("username", currentUserName);
                context.startActivity(intent);
            }
        }
    }

    @Override
    public int getItemCount() {
        return this.shopList.size();
    }

    public void setCurrentUserName(String newName){
        currentUserName = newName;
    }

}
