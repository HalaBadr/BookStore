package com.example.hala.bookstore.view.profile.fragments;

import android.content.Context;
import android.widget.Toast;

import com.example.hala.bookstore.Interface.OnRegisterFinishedListner;
import com.example.hala.bookstore.network.APIInterface;
import com.example.hala.bookstore.network.api.APIClient;
import com.example.hala.bookstore.network.models.SignForm;
import com.example.hala.bookstore.network.models.Token;
import com.example.hala.bookstore.utils.PrefManager;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInteractor {

    void register(String name, String email, String phone, String address, String password,
                  OnRegisterFinishedListner onRegisterFinishedListner, Context context) {

        SignForm signUp = new SignForm(name, email, phone, address, password);

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

        apiInterface.register(signUp).enqueue(new Callback<Token>() {
            @Override
            public void onResponse(@NotNull Call<Token> call,
                                   @NotNull Response<Token> response) {
                if (response.isSuccessful()){
                    PrefManager.saveToken(context, response.body().getAccessToken());
                    onRegisterFinishedListner.onSuccess();
                    Toast.makeText(context, "Success!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Auth error!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<Token> call,
                                  @NotNull Throwable t) {
                onRegisterFinishedListner.onError();
            }
        });

    }

    void login(String email, String password,
               OnRegisterFinishedListner onRegisterFinishedListner, Context context) {

        SignForm signIn = new SignForm(email, password);

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

        apiInterface.login(signIn).enqueue(new Callback<Token>() {
            @Override
            public void onResponse(@NotNull Call<Token> call,
                                   @NotNull Response<Token> response) {
                if (response.isSuccessful()){
                    PrefManager.saveToken(context, response.body().getAccessToken());
                    onRegisterFinishedListner.onSuccess();
                    Toast.makeText(context, "Success!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Auth error!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<Token> call,
                                  @NotNull Throwable t) {
                onRegisterFinishedListner.onError();
            }
        });

    }
}


