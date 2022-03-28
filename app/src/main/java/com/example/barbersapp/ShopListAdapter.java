package com.example.barbersapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class ShopListAdapter extends RecyclerView.Adapter<ShopListAdapter.ShopViewHolder> {

    private Context context;
    private List<Shop> shopList;

    public ShopListAdapter(Context context){
        this.context = context;
    }

    public void setShopList(List<Shop> shopList){
        this.shopList = shopList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ShopListAdapter.ShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_row_shop,parent,false);
        return new ShopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopListAdapter.ShopViewHolder holder, int position) {
        holder.shopNameText.setText(this.shopList.get(position).getShopName());
        holder.shopAddressText.setText(this.shopList.get(position).getShopAddress());
    }

    @Override
    public int getItemCount() {
        return this.shopList.size();
    }

    public class ShopViewHolder extends RecyclerView.ViewHolder{

        TextView shopNameText, shopAddressText;

        public ShopViewHolder(View view){
            super(view);
            shopNameText = view.findViewById(R.id.shopNameText);
            shopAddressText = view.findViewById(R.id.shopAddressText);
        }
    }

}
