package com.igorr.homework_8;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import main.fragments.FragmentGreen;
import main.fragments.FragmentPurple;
import main.fragments.FragmentRed;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fragManager;

    @BindView(R.id.drawerLayout)
    protected DrawerLayout drawerLayout;
    @BindView(R.id.toolBar)
    protected Toolbar toolbar;
    @BindView(R.id.navigationView)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        fragManager = getSupportFragmentManager();

        setSupportActionBar(toolbar);
        if (savedInstanceState != null) {
            getSupportActionBar().setTitle(savedInstanceState.getCharSequence("title"));
        }
        //Добавить значок слева
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,
                toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        //Слушатель событий для DrawerLayout
        navigationView.setNavigationItemSelectedListener(this);
    }

    //Сохранение названия
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putCharSequence("title", getSupportActionBar().getTitle());
    }

    //Действия кнопки "назад"(чтобы не сворачивала приложение)
    @Override
    public void onBackPressed() {
        if (!fragManager.getFragments().isEmpty()) fragManager.popBackStack();
        else Toast.makeText(this, "Нет фрагментов в стеке", Toast.LENGTH_LONG).show();
    }

    //Обработчик меню в DrawerLayout
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentTransaction transaction = fragManager.beginTransaction();
        toolbar.setTitle(item.getTitle());
        switch (item.getItemId()) {
            case R.id.greenFragment:
                transaction.replace(R.id.frameContent, new FragmentGreen());
                break;
            case R.id.redFragment:
                transaction.replace(R.id.frameContent, new FragmentRed());
                break;
            case R.id.purpleFragment:
                transaction.replace(R.id.frameContent, new FragmentPurple());
                break;
            case R.id.mNextActivity:
                drawerLayout.closeDrawer(GravityCompat.START);
                startActivity(new Intent(this, NextActivity.class));
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        transaction.addToBackStack(null).commit();
        return true;
    }
}