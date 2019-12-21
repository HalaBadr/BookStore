package com.example.hala.bookstore.view.profile.fragments;

import android.content.Context;
import android.util.Patterns;

import com.example.hala.bookstore.Interface.OnRegisterFinishedListner;
import com.example.hala.bookstore.Interface.Sign;

public class SignPresenter  implements OnRegisterFinishedListner {

    private Sign signView;
    private SignInteractor signInteractor;

    public SignPresenter(Sign signView, SignInteractor signInteractor) {
        this.signView = signView;
        this.signInteractor = signInteractor;
    }

    void checkRegister(String name, String email, String phone, String address, String password,
                       Context context) {
        if (name.isEmpty()) {
            signView.nameError();
        }else if (email.isEmpty()) {
            signView.emailError("Empty Email");
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            signView.emailError("Invalid Email");
        }else if (phone.isEmpty()) {
            signView.phoneError();
        }else if (address.isEmpty()) {
            signView.addressError();
        } else if (password.isEmpty()) {
            signView.passwordError();
        }else {
            signInteractor.register(name, email, phone, address, password,
                    this, context);
        }
    }

    void checkLogin(String email, String password, Context context) {
        if (email.isEmpty()) {
            signView.emailError("Empty Email");
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            signView.emailError("Invalid Email");
        }else if (password.isEmpty()) {
            signView.passwordError();
        }else {
            signInteractor.login(email, password, this, context);
        }
    }

    @Override
    public void onSuccess() {
        signView.validCradintial();
    }

    @Override
    public void onError() {
        signView.inValidCradintial();
    }
}

