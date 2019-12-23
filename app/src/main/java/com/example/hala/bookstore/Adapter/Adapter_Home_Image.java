package com.example.hala.bookstore.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hala.bookstore.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter_Home_Image extends RecyclerView.Adapter<Holder>{


    private List<String> model = new ArrayList<>();
    private Context context;
    private String layout;


    public Adapter_Home_Image(List<String> model, Context context) {
        this.model = model;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent,
                false);

        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Picasso.get()
                .load(model.get(position))
                .placeholder(R.drawable.ic_image)
                .into(holder.homeslider);

    }

    @Override
    public int getItemCount() {
        return model.size();
    }
}
