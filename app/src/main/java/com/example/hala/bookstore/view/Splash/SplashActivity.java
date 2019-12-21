package com.example.hala.bookstore.view.Splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.hala.bookstore.view.MainActivity;
import com.example.hala.bookstore.R;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(
                this::gotoMain,
                SPLASH_TIME_OUT);
    }

    private void gotoMain(){
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }

}
