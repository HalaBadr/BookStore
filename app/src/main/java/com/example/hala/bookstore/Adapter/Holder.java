package com.example.hala.bookstore.Adapter;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.hala.bookstore.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Holder  extends RecyclerView.ViewHolder {

    public ImageView homeslider;
    private ImageView bookImage;
    private TextView bookName;
    private TextView bookAuthorName;
    private TextView bookPrice;
    private TextView bookPriceOld;
    private View bookPriceView;
    private Button bookFree;
    private RatingBar ratingBar;
    private Button openBook;
    public static String url;

    public Holder(@NonNull View itemView) {
        super(itemView);
        homeslider = (ImageView) itemView.findViewById(R.id.image_item);

        bookImage = (ImageView) itemView.findViewById(R.id.book_image);
        bookName = (TextView) itemView.findViewById(R.id.book_name);
        bookAuthorName = (TextView) itemView.findViewById(R.id.book_author_name);
        bookPrice = (TextView) itemView.findViewById(R.id.book_price);
        bookPriceOld = (TextView) itemView.findViewById(R.id.book_price_old);
        bookPriceView = (View) itemView.findViewById(R.id.book_price_view);
        bookFree = (Button) itemView.findViewById(R.id.book_free);
        ratingBar = (RatingBar) itemView.findViewById(R.id.rating);
        openBook = (Button) itemView.findViewById(R.id.open_book);


    }
}
