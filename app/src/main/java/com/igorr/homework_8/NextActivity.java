package com.igorr.homework_8;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import main.fragments.FragmentGreen;
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

    //Добавить меню справа
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
                addFragment(new FragmentRed(), R.string.save);
                return true;
            case R.id.load:
                addFragment(new FragmentGreen(), R.string.load);
                return true;
            case R.id.nextActivity:
                addFragment(new FragmentVPager(), R.string.title_next);
                return true;
        }
        return false;
    }

    private void addFragment(Fragment fragment, int msg) {
        try {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.d("exception - ", e.toString());
        }
        FragmentTransaction transaction = fragManager.beginTransaction();
        transaction.replace(R.id.mainFrameContent, fragment);
        transaction.addToBackStack(null).commit();
    }
}
