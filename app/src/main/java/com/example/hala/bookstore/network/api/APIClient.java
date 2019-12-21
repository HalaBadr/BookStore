package com.example.hala.bookstore.network.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    public  static Retrofit retrofit = null;

    public static final String Base_url     =   "https://node-book-store.herokuapp.com/api/";
    public static final String images_url   =   Base_url +"books/home/slider";
    public static final String books_url    =   Base_url + "books/";
    public static final String MyBooks      =   Base_url + "users/books";
    public static final String UserRegister =   Base_url + "users/register";
    public static final String UserLogin    =   Base_url + "users/login";
    public static final String GetMe        =   Base_url + "users/me";
    public static final String BuyBook      =   Base_url + "books/buy/";

    public static Retrofit getClient(){
        if (retrofit == null){
            retrofit = new Retrofit
                    .Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Base_url)
                    .build();
        }
        return retrofit;
    }


}
