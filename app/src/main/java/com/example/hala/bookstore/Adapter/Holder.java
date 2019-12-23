package com.example.hala.bookstore.Adapter;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.hala.bookstore.R;
import com.example.hala.bookstore.network.models.ItemModel;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.hala.bookstore.utils.Enum;

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



    public void bind(ItemModel itemModel, Context context, Enum layout){

        Picasso.get()
                .load(itemModel.getImage_book())
                .placeholder(R.drawable.ic_image)
                .into(bookImage);
        bookName.setText(itemModel.getBook_name());

        if (layout == Enum.Home){
            getPrice(itemModel);
        }
        else
        {
            bookAuthorName.setText(itemModel.getAuthor());
            if (layout == Enum.Featured){
                ratingBar.setNumStars(itemModel.getTotalRating());
                ratingBar.setVisibility(View.VISIBLE);
                getPrice(itemModel);
            }
            else {
                url = itemModel.getUrl();
                openBook.setVisibility(View.VISIBLE);

            }
        }

    }

    private void getPrice(ItemModel itemModel){
        if (itemModel.getBook_price() == 0 && itemModel.getDiscount() == 0){
            bookFree.setVisibility(View.VISIBLE);
        }else if (itemModel.getDiscount() == 0){
            bookPrice.setText("$ " + String.valueOf(itemModel.getBook_price()));
            bookPrice.setVisibility(View.VISIBLE);
        }else {
            double discount = itemModel.getBook_price() -
                    (itemModel.getBook_price() * itemModel.getDiscount() / 100);
            bookPrice.setText("$ " + String.valueOf(discount));
            bookPrice.setVisibility(View.VISIBLE);
            bookPriceOld.setText("$ " + (String.valueOf(itemModel.getBook_price())));
            bookPriceOld.setVisibility(View.VISIBLE);
            bookPriceView.setVisibility(View.VISIBLE);
        }
    }
}


