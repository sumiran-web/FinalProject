package com.example.restaurantfinder.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.restaurantfinder.R;
import com.example.restaurantfinder.activity.RestaurantDetailActivity;
import com.example.restaurantfinder.modal.ProductModal;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerviewProductAdapter extends RecyclerView.Adapter<RecyclerviewProductAdapter.MyViewHolder>{

    Context context;
    List<ProductModal> productModals;

    public RecyclerviewProductAdapter(Context context, List<ProductModal> productModals) {
        this.context = context;
        this.productModals = productModals;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        //// getting data according to position
        final ProductModal productModal = productModals.get(position);

        holder.txt_restaurant_name.setText(productModal.getName());
        holder.txt_restaurant_desc.setText(productModal.getLocation());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RestaurantDetailActivity.productID = productModal.get_id();
                Intent intent = new Intent(context, RestaurantDetailActivity.class);
                intent.putExtra("_name",productModal.getName());
                intent.putExtra("_desc",productModal.getDescription());
                intent.putExtra("_price",productModal.getLocation());
                intent.putExtra("_img", productModal.getImage());
                context.startActivity(intent);
            }
        });



        // get Product Image
        Picasso.get()
                .load(productModal.getImage())
                .placeholder(R.drawable.ic_launcher_background)
                .resize(220, 220)
                .centerCrop()
                .into(holder.img_photo);
    }

    @Override
    public int getItemCount() {
        return productModals.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView txt_restaurant_name,txt_restaurant_desc;
        ImageView img_photo;

        public MyViewHolder(View itemView) {
            super(itemView);
            txt_restaurant_name = itemView.findViewById(R.id.txtProductName);
            txt_restaurant_desc = itemView.findViewById(R.id.txtPrice);
            img_photo = itemView.findViewById(R.id.imgProduct);
        }
    }
}