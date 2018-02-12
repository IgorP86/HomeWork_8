package com.igorr.homework_8;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import nextActv.fragments.FragmentLoad;
import nextActv.fragments.FragmentSave;
import nextActv.fragments.FragmentVPager;
import nextActv.fragments.TitleChangeListener;

/**
 * Created by Igorr on 02.02.2018.
 */

public class NextActivity extends AppCompatActivity implements TitleChangeListener {
    @BindView(R.id.toolBar)
    Toolbar toolbar;
    private FragmentManager fragManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        ButterKnife.bind(this);
        fragManager = getSupportFragmentManager();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
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
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    //Обработчик меню "справа"
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        try {
            View background = findViewById(R.id.pageBackground);
            switch (item.getItemId()) {
                case R.id.mOrange:
                    background.setBackgroundColor(getResources().getColor(R.color.colorOrange));
                    break;
                case R.id.mEmerald:
                    background.setBackgroundColor(getResources().getColor(R.color.colorEmerald));
                    break;
                case R.id.mAzure:
                    background.setBackgroundColor(getResources().getColor(R.color.colorAzure));
                    break;
                case android.R.id.home:
                    fragManager.popBackStack();
                    break;
            }
        } catch (Exception e) {
            Toast.makeText(this, "Нет фрагментов", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    //Обработка BottomNavigation
    private boolean selector(MenuItem item) {
        toolbar.getMenu().setGroupVisible(R.id.group_hide, true);
        FragmentTransaction transaction = fragManager.beginTransaction();
        switch (item.getItemId()) {
            case R.id.mSave:
                transaction.replace(R.id.frameContent, new FragmentSave());
                break;
            case R.id.mLoad:
                transaction.replace(R.id.frameContent, new FragmentLoad());
                break;
            case R.id.mNextActivity:
                transaction.replace(R.id.frameContent, new FragmentVPager());
                toolbar.getMenu().setGroupVisible(R.id.group_hide, false);
                break;
        }
        transaction.addToBackStack(null).commit();
        return true;
    }

    @Override
    public void titleChange(CharSequence title) {
        toolbar.setTitle(title);
    }
}