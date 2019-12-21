package com.example.hala.bookstore.network;


import com.example.hala.bookstore.network.api.APIClient;
import com.example.hala.bookstore.network.models.ItemModel;
import com.example.hala.bookstore.network.models.MyBooks;
import com.example.hala.bookstore.network.models.SignForm;
import com.example.hala.bookstore.network.models.Token;
import com.example.hala.bookstore.network.models.UserInfo;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIInterface {

    @GET( APIClient.images_url)
    Call<List<String>> getImages();

    @GET(APIClient.books_url)
    Call<List<ItemModel>> getBooks();

    @GET(APIClient.books_url + "{id}")
    Call<ItemModel> getBookById(@Path("id")String id);

    @GET(APIClient.MyBooks)
    Call<MyBooks> getMyBooks(@Header("x-auth-token") String token);

    @POST(APIClient.UserRegister)
    Call<Token> register(@Body SignForm signForm);

    @POST(APIClient.UserLogin)
    Call<Token> login(@Body SignForm signForm);

    @GET(APIClient.GetMe)
    Call<UserInfo> getMy(@Header("x-auth-token") String token);

    @POST(APIClient.BuyBook + "{id}")
    Call<UserInfo> BuyBook(@Path("id")String id, @Header("x-auth-token") String token);

}
