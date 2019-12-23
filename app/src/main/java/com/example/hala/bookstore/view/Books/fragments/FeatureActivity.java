package com.example.hala.bookstore.view.Books.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hala.bookstore.Adapter.Adpter_Books;
import com.example.hala.bookstore.Adapter.onItemClicked;
import com.example.hala.bookstore.R;
import com.example.hala.bookstore.network.APIInterface;
import com.example.hala.bookstore.network.api.APIClient;
import com.example.hala.bookstore.network.models.ItemModel;
import com.example.hala.bookstore.utils.Enum;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeatureActivity extends AppCompatActivity implements onItemClicked, View.OnClickListener {


    @BindView(R.id.back_feature)
    TextView backFeature;
    List<ItemModel> featuredBooks;
    RecyclerView featureRecycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feature);
        ButterKnife.bind(this);

        featureRecycleView = (RecyclerView) findViewById(R.id.feature_recycleview);
        featuredBooks = new ArrayList<>();

        backFeature.setOnClickListener(this);

        getBooks();

    }

    void getBooks() {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        apiInterface.getBooks().enqueue(new Callback<List<ItemModel>>() {
            @Override
            public void onResponse(@NotNull Call<List<ItemModel>> call,
                                   @NotNull Response<List<ItemModel>> response) {
                if (response.isSuccessful()) {

                    featuredBooks = response.body();

                    Adpter_Books booksAdapter = new Adpter_Books(featuredBooks, FeatureActivity.this,
                            FeatureActivity.this, Enum.Featured);

                    LinearLayoutManager linearLayoutManager_books = new
                            LinearLayoutManager(FeatureActivity.this,
                            LinearLayoutManager.VERTICAL, false);

                    featureRecycleView.setLayoutManager(linearLayoutManager_books);

                    featureRecycleView.setAdapter(booksAdapter);
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<ItemModel>> call,
                                  @NotNull Throwable t) {
                Toast.makeText(FeatureActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onClick(int position) {
        Intent intent = new Intent(FeatureActivity.this, BookDetailsActivity.class);
        intent.putExtra("BOOK", (Serializable) featuredBooks.get(position));
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        super.onBackPressed();
        finish();
    }
}
