package com.example.hala.bookstore.view.profile.fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.hala.bookstore.R;
import com.example.hala.bookstore.utils.PrefManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ProfileFragment extends Fragment {


    Unbinder unbinder;
    @BindView(R.id.profile_frag)
    FrameLayout profileFrag;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        if (PrefManager.getToken(getActivity()) == null) {
            openFrag(new ProfileSignFragment());
        } else {
            openFrag(new ProfileUserFragment());
        }

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    private void openFrag(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.profile_frag, fragment);
        transaction.commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
