package com.example.hala.bookstore.view.Books.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.hala.bookstore.Adapter.Adpter_Books;
import com.example.hala.bookstore.Adapter.onItemClicked;
import com.example.hala.bookstore.R;
import com.example.hala.bookstore.network.APIInterface;
import com.example.hala.bookstore.network.api.APIClient;
import com.example.hala.bookstore.network.models.ItemModel;
import com.example.hala.bookstore.network.models.MyBooks;
import com.example.hala.bookstore.utils.PrefManager;
import com.example.hala.bookstore.utils.Enum;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BooksFragment extends Fragment implements onItemClicked {


    RecyclerView booksRecycleView;

    List<ItemModel> MyBook;

    Button open_book;

    public BooksFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_books, container, false);

        booksRecycleView = (RecyclerView) view.findViewById(R.id.books_recycleview);

        MyBook = new ArrayList<>();
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

        if (PrefManager.getToken(getActivity()) == null) {
            Toast.makeText(getActivity(), "Sign Up ", Toast.LENGTH_SHORT).show();
        } else {

            apiInterface.getMyBooks(PrefManager.getToken(getActivity())).
                    enqueue(new Callback<MyBooks>() {
                        @Override
                        public void onResponse(@NotNull Call<MyBooks> call,
                                               @NotNull Response<MyBooks> response) {

                            if (response.isSuccessful()){

                                MyBook = response.body().getBooks();

                                Adpter_Books booksAdapter =
                                        new Adpter_Books(MyBook, getActivity(),
                                        BooksFragment.this, Enum.User);

                                LinearLayoutManager linearLayoutManager_books = new
                                        LinearLayoutManager(getActivity(),
                                        LinearLayoutManager.VERTICAL, false);

                                booksRecycleView.setLayoutManager(linearLayoutManager_books);

                                booksRecycleView.setAdapter(booksAdapter);
                            }

                        }

                        @Override
                        public void onFailure(@NotNull Call<MyBooks> call,
                                              @NotNull Throwable t) {

                            Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
        return view;
    }


    @Override
    public void onClick(int position) {
        Intent intent = new Intent(getActivity(), BookDetailsActivity.class);
        intent.putExtra("BOOK", (Serializable) MyBook.get(position));
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
