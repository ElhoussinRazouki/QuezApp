package com.example.quezapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.quezapp.Fragment.StartQuizFragment;
import com.example.quezapp.Fragment.UserHistoryFragment;
import com.example.quezapp.Fragment.UserProfileFragment;
import com.example.quezapp.Fragment.beginQuizFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import Dao.Db.Level_Dao;
import Dao.Db.User_Dao;

public class userActivity extends AppCompatActivity implements StartQuizFragment.SurClickListener {
    int idUser ;

    private Toolbar tolbar ;
    TextView txtFragment ;
    Bundle bundle = new Bundle();



    StartQuizFragment startQuizFragment;
    BottomNavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_main);
        navigationView = findViewById(R.id.bottomNavigationView);
        txtFragment = findViewById(R.id.nameFragment);
        tolbar = findViewById(R.id.main_toolbar_user);
        this.setSupportActionBar(tolbar);
        getSupportActionBar().setTitle(null);

        startQuizFragment = new StartQuizFragment(this);

        bundle.putInt(User_Dao.Column_Id,this.getIntent().getIntExtra(User_Dao.Column_Id,0));
        startQuizFragment.setArguments(bundle);
        changeFragment(startQuizFragment);
        txtFragment.setText("Home");

        navigationView.setOnItemSelectedListener(item -> {
            switch(item.getItemId()){
                case R.id.home:
                    txtFragment.setText("Home");
                    changeFragment(startQuizFragment);
                    break;
                case R.id.history:
                    txtFragment.setText("History");
                    UserHistoryFragment userHistoryFragment =new UserHistoryFragment();
                    userHistoryFragment.setArguments(bundle);
                    changeFragment(userHistoryFragment);
                    break;
                case R.id.profile:
                    txtFragment.setText("Profile");
                    UserProfileFragment userProfileFragment = new UserProfileFragment();
                    userProfileFragment.setArguments(bundle);
                    changeFragment(userProfileFragment);
                    break;
            }
            return true;
        });

    }
    public void changeFragment(Fragment fragment){
        this.getSupportFragmentManager().beginTransaction().replace(R.id.Frame_Layout_MainActivity_user,fragment).commit();
    }

    @Override
    public void surClick(View view, Bundle Data) {
        switch (view.getId()){
            case R.id.btn_start_quiz:
//                Bundle bn = new Bundle();
//                bn.putInt(Level_Dao.COLUMN_ID, Data);
                beginQuizFragment beginQuizFragment = new beginQuizFragment();
                beginQuizFragment.setArguments(Data);
                this.getSupportFragmentManager().beginTransaction().replace(R.id.Frame_Layout_MainActivity_user,beginQuizFragment).commit();
                break;
        }

    }
}