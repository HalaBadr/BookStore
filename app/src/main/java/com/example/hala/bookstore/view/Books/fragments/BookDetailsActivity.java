package com.example.hala.bookstore.view.Books.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hala.bookstore.R;
import com.example.hala.bookstore.network.APIInterface;
import com.example.hala.bookstore.network.api.APIClient;
import com.example.hala.bookstore.network.models.ItemModel;
import com.example.hala.bookstore.network.models.UserInfo;
import com.example.hala.bookstore.utils.PrefManager;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookDetailsActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.book_details)
    TextView bookDetails;
    @BindView(R.id.book_image_details)
    ImageView bookImageDetails;
    @BindView(R.id.book_image_card)
    CardView bookImageCard;
    @BindView(R.id.book_name_details)
    TextView bookNameDetails;
    @BindView(R.id.book_author_name_details)
    TextView bookAuthorNameDetails;
    @BindView(R.id.book_price_details)
    TextView bookPriceDetails;
    @BindView(R.id.rating)
    RatingBar rating;
    @BindView(R.id.rating_tv)
    TextView ratingTv;
    @BindView(R.id.bookInformation)
    TextView bookInformation;
    @BindView(R.id.aboutAuthor)
    TextView aboutAuthor;
    @BindView(R.id.place_order)
    Button placeOrder;

    private ItemModel itemModel;
    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        ButterKnife.bind(this);
        itemModel = (ItemModel) getIntent().getSerializableExtra("BOOK");

        assert itemModel != null;
        id = itemModel.getId();

        bookDetails.setOnClickListener(this);
        placeOrder.setOnClickListener(this);

        rating.setOnRatingBarChangeListener((ratingBar, v, b) -> {
            Toast.makeText(BookDetailsActivity.this, "Stars: " + v, Toast.LENGTH_SHORT).show();
            itemModel.setRating(v);
        });

        getBook();

    }

    void getBook() {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

        apiInterface.getBookById(id).enqueue(new Callback<ItemModel>() {
            @Override
            public void onResponse(@NotNull Call<ItemModel> call,
                                   @NotNull Response<ItemModel> response) {
                if (response.isSuccessful()) {

                    Picasso.get()
                            .load(response.body().getImage_book())
                            .placeholder(R.drawable.ic_image)
                            .into(bookImageDetails);
                    bookNameDetails.setText(response.body().getBook_name());
                    bookAuthorNameDetails.setText(response.body().getAuthor());
                    bookPriceDetails.setText("$" + response.body().getBook_price());
                    bookInformation.setText(response.body().getBookDescription());
                    aboutAuthor.setText(response.body().getAboutAuthor());
                    //rating.setNumStars(response.body().getTotalRating());
                    // ratingTv.setText(response.body().getTotalRating());
                }
            }

            @Override
            public void onFailure(@NotNull Call<ItemModel> call,
                                  @NotNull Throwable t) {
                Toast.makeText(BookDetailsActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    void buyBook() {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

        apiInterface.BuyBook(id, PrefManager.getToken(BookDetailsActivity.this))
                .enqueue(new Callback<UserInfo>() {
                    @Override
                    public void onResponse(@NotNull Call<UserInfo> call,
                                           @NotNull Response<UserInfo> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(BookDetailsActivity.this, "Buying Success",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(BookDetailsActivity.this, "Buying fail",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<UserInfo> call,
                                          @NotNull Throwable t) {
                        Toast.makeText(BookDetailsActivity.this, t.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }



    @Override
    public void onClick(View view) {

    }
}
