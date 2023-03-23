package com.example.quezapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.example.quezapp.Fragment.LevelFragment;
import com.google.android.material.navigation.NavigationView;

import com.example.quezapp.Fragment.CategorieFragment;

import Dao.Class.Question;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar tolbar ;
    private DrawerLayout drawerLay ;
    private LinearLayout lin ;
    private NavigationView navView;
    private ActionBarDrawerToggle toggle ;
    private SaisieFragment saisieFragment ;
    private QuizFragment quizFragment;
    private AffichageFragment affichageFragment ;
    private CategorieFragment categorieFragment ;
    private LevelFragment levelFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tolbar = findViewById(R.id.main_toolbar);
        this.setSupportActionBar(tolbar);
        this.lin = findViewById(R.id.linear_layout);
        this.drawerLay = findViewById(R.id.drawer_Layout_MainActivity);
        this.navView = findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(this);
        navView.setItemIconTintList(null);
        this.toggle = new ActionBarDrawerToggle(this,this.drawerLay,this.tolbar,R.string.open,R.string.close);
        this.drawerLay.addDrawerListener(this.toggle);
        this.toggle.syncState();
        this.getSupportFragmentManager().beginTransaction().replace(R.id.Frame_Layout_MainActivity,new SaisieFragment()).commit();
    }
    public void changeFragment(int id , Fragment fragment){
        this.getSupportFragmentManager().beginTransaction().replace(id,fragment).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case  R.id.menuSaisie:
                if(saisieFragment == null)
                {
                    saisieFragment = new SaisieFragment();
                }
                if(!saisieFragment.isVisible()){
                    this.getSupportFragmentManager().beginTransaction().replace(R.id.Frame_Layout_MainActivity,saisieFragment).commit();
                }
                break;
            case  R.id.menuQuiz:
                if(quizFragment == null ){
                    quizFragment = new QuizFragment();
                }
                if(!quizFragment.isVisible()){
                    this.getSupportFragmentManager().beginTransaction().replace(R.id.Frame_Layout_MainActivity,quizFragment).commit();
                }
                break;
            case  R.id.menuAfficher:
                if(affichageFragment == null ){
                    affichageFragment = new AffichageFragment();
                }
                if(!affichageFragment.isVisible()){
                    this.getSupportFragmentManager().beginTransaction().replace(R.id.Frame_Layout_MainActivity,affichageFragment).commit();
                }
                break;
            case  R.id.menuCategorie:
                if(categorieFragment == null ){
                    categorieFragment = new CategorieFragment();
                }
                if(!categorieFragment.isVisible()){
                    this.getSupportFragmentManager().beginTransaction().replace(R.id.Frame_Layout_MainActivity,categorieFragment).commit();
                }
                break;
            case  R.id.menuLevel:
                if(levelFragment == null ){
                    levelFragment = new LevelFragment();
                }
                if(!levelFragment.isVisible()){
                    this.getSupportFragmentManager().beginTransaction().replace(R.id.Frame_Layout_MainActivity,levelFragment).commit();
                }
                break;
        }
        this.drawerLay.closeDrawer(Gravity.LEFT);

        return false;
    }

}