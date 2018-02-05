package com.igorr.homework_8;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import main.fragments.FragmentGreen;
import main.fragments.FragmentPurple;
import main.fragments.FragmentRed;

/**
 * Created by Igorr on 02.02.2018.
 */

public class NextActivity extends AppCompatActivity {
    @BindView(R.id.toolBar)
    Toolbar toolbar;
    private FragmentManager fragManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        fragManager = getSupportFragmentManager();
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        //Обработчик BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        return selector(item);
                    }
                });
    }

    //Добавить меню справа (три точки)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    //Обработчик меню "справа"
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        return selector(item);
    }

    private boolean selector(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                showScreen(new FragmentRed(), R.string.save);
                return true;
            case R.id.load:
                showScreen(new FragmentGreen(), R.string.load);
                return true;
            case R.id.nextActivity:
                showScreen(new FragmentVPager(), R.string.title_next);
                return true;
        }
        return false;
    }

    private void showScreen(Fragment fragment, int msg) {
        if (msg != 0) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            Log.d("showScreen", "msg != 0");
        }
        FragmentTransaction transaction = fragManager.beginTransaction();
        transaction.replace(R.id.mainFrameContent, fragment);
        transaction.addToBackStack(null).commit();
    }
}