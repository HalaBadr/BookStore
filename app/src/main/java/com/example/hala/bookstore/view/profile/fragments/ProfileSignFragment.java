package com.example.hala.bookstore.view.profile.fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hala.bookstore.R;
import com.example.hala.bookstore.Interface.Sign;

import androidx.constraintlayout.widget.Group;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ProfileSignFragment extends Fragment implements Sign {

    Unbinder unbinder;
    @BindView(R.id.sign_btn)
    Button signBtn;
    @BindView(R.id.group_sign)
    Group groupSign;
    @BindView(R.id.sign_up_tv)
    TextView signUpTv;
    @BindView(R.id.sign_in_tv)
    TextView signInTv;
    @BindView(R.id.sign_name_edt)
    EditText signName;
    @BindView(R.id.sign_mobile_edt)
    EditText signMobile;
    @BindView(R.id.sign_email_edt)
    EditText signEmail;
    @BindView(R.id.sign_address_edt)
    EditText signAddress;
    @BindView(R.id.sign_password_edt)
    EditText signPassword;

    SignPresenter signPresenter = new SignPresenter(this, new SignInteractor());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_sign, container, false);
        unbinder = ButterKnife.bind(this, view);

        signBtn.setOnClickListener(this::onViewClicked);

        signUpTv.setOnClickListener(this::onViewClicked);
        signInTv.setOnClickListener(this::onViewClicked);


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sign_btn:
                String email = signEmail.getText().toString();
                String password = signPassword.getText().toString();
                if (groupSign.getVisibility() == View.VISIBLE) {
                    String name = signName.getText().toString();
                    String phone = signMobile.getText().toString();
                    String address = signAddress.getText().toString();
                    signPresenter.checkRegister(name, email, phone, address, password, getActivity());
                } else {
                    signPresenter.checkLogin(email, password, getActivity());
                }
                break;

            case R.id.sign_up_tv:
                groupSign.setVisibility(View.VISIBLE);
                signUpTv.setTextAppearance(getActivity(), R.style.ButtonClicked);
                signInTv.setTextAppearance(getActivity(), R.style.ButtonDisable);
                signBtn.setText("Sign Up");
                break;
            case R.id.sign_in_tv:
                groupSign.setVisibility(View.GONE);
                signInTv.setTextAppearance(getActivity(), R.style.ButtonClicked);
                signUpTv.setTextAppearance(getActivity(), R.style.ButtonDisable);
                signBtn.setText("Sign In");
                break;
        }
    }

    @Override
    public void nameError() {
        signName.setError("Error name");
    }

    @Override
    public void emailError(String error) {
        signEmail.setError(error);
    }

    @Override
    public void phoneError() {
        signMobile.setError("Error phone");
    }

    @Override
    public void addressError() {
        signAddress.setError("Error address");
    }

    @Override
    public void passwordError() {
        signPassword.setError("Error password");
    }

    @Override
    public void validCradintial() {

        Toast.makeText(getActivity(), "succcess", Toast.LENGTH_SHORT).show();
        signName.setText("");
        signEmail.setText("");
        signMobile.setText("");
        signAddress.setText("");
        signPassword.setText("");

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.profile_frag, new ProfileUserFragment());
        transaction.commit();

    }

    @Override
    public void inValidCradintial() {
        Toast.makeText(getActivity(), "Invalid email or password", Toast.LENGTH_SHORT).show();
    }

}
