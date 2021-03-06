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

public class ShopListAdapter extends RecyclerView.Adapter<ShopListAdapter.ShopViewHolder> {

    private Context context;
    private List<Shop> shopList;

    public ShopListAdapter(Context context) {
        this.context = context;
    }

    public void setShopList(List<Shop> shopList) {
        this.shopList = shopList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ShopListAdapter.ShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_row_shop, parent, false);
        return new ShopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopListAdapter.ShopViewHolder holder, int position) {
        holder.shopName.setText(this.shopList.get(position).getShopName());
        holder.shopAddress.setText(this.shopList.get(position).getShopAddress());
    }

    public class ShopViewHolder extends RecyclerView.ViewHolder {

        TextView shopName, shopAddress;
        Button viewDetailsButton;

        public ShopViewHolder(View view) {
            super(view);
            shopName = view.findViewById(R.id.shopNameText);
            shopAddress = view.findViewById(R.id.shopAddressText);
            viewDetailsButton = view.findViewById(R.id.detailsButton);
            viewDetailsButton.setOnClickListener(this::onClick);

        }

        public void onClick(View v) {
            if (v.getId() == viewDetailsButton.getId()) {
                Intent intent = new Intent(context, ShopDetailsActivity.class);
                intent.putExtra("shopname", shopName.getText().toString());
                context.startActivity(intent);
            }
        }
    }

    @Override
    public int getItemCount() {
        return this.shopList.size();
    }

}
