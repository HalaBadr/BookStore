package com.example.hala.bookstore.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.hala.bookstore.R;
import com.example.hala.bookstore.view.Books.fragments.BooksFragment;
import com.example.hala.bookstore.view.Home.fragments.HomeFragment;
import com.example.hala.bookstore.view.profile.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.main_frag)
    FrameLayout mainFrag;
    @BindView(R.id.nav_btn)
    BottomNavigationView navBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        navBtn.setOnNavigationItemSelectedListener(this);
        openFrag(new HomeFragment());

    }

    private void openFrag(Fragment fragment){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.main_frag , fragment);
        transaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.home:
                openFrag(new HomeFragment());
                return true;
            case R.id.books:
                openFrag(new BooksFragment());
                return true;
            case R.id.profile:
                openFrag(new ProfileFragment());
                return true;
        }
        return false;
    }
}
