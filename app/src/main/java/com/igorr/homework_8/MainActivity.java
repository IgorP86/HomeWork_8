package com.igorr.homework_8;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
    Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        fragManager = getSupportFragmentManager();

        setSupportActionBar(toolBar);

        //Добавить значок слева
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,
                toolBar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);
    }

    //Действия кнопки "назад"
    @Override
    public void onBackPressed() {
        if (fragManager.getFragments().isEmpty())
            Toast.makeText(this, "Нет фрагментов в стеке", Toast.LENGTH_LONG).show();
        super.onBackPressed();
    }

    //Обработчик меню в DrawerLayout
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        FragmentTransaction transaction = fragManager.beginTransaction();
        if (item.getItemId() == R.id.greenFragment) {
            transaction.add(R.id.mainFrameContent, new FragmentGreen());
        } else if (item.getItemId() == R.id.redFragment)
            transaction.replace(R.id.mainFrameContent, new FragmentRed());
        else if (item.getItemId() == R.id.purpleFragment)
            transaction.replace(R.id.mainFrameContent, new FragmentPurple());
        else if (item.getItemId() == R.id.nextActivity) {
            drawerLayout.closeDrawer(GravityCompat.START);
            startActivity(new Intent(this, NextActivity.class));
            return true;
        }
        //Поменять название
        toolBar.setTitle(item.getTitle());
        //убрать DrawerLayout
        drawerLayout.closeDrawer(GravityCompat.START);
        transaction.addToBackStack(null).commit();
        return true;
    }


}