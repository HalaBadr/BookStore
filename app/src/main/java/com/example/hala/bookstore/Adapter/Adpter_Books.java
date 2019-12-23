package com.example.hala.bookstore.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hala.bookstore.R;
import com.example.hala.bookstore.network.models.ItemModel;
import com.example.hala.bookstore.utils.Enum;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class Adpter_Books extends RecyclerView.Adapter<Holder>{

    private List<ItemModel> itemModels;
    private Context context;
    private onItemClicked onItemClicked;
    public Enum layout;

    public Adpter_Books( List<ItemModel> itemModels, Context context,onItemClicked onItemClicked, Enum layout) {
        this.context = context;
        this.itemModels = itemModels;
        this.onItemClicked = onItemClicked;
        this.layout = layout;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = null;
        switch (layout){
            case Home:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_home, parent,false);
                break;
            case User:
            case Featured:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent,false);
                break;
        }
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.bind(itemModels.get(position), context, layout);
        holder.itemView.setOnClickListener(view -> onItemClicked.onClick(position));
    }

    @Override
    public int getItemCount() {
        return itemModels.size();
    }
}
