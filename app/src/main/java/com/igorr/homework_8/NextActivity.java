package com.igorr.homework_8;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import nextActv.fragments.ActionListener;
import nextActv.fragments.FragmentLoad;
import nextActv.fragments.FragmentSave;
import nextActv.fragments.FragmentVPager;

/**
 * Created by Igorr on 02.02.2018.
 */

public class NextActivity extends AppCompatActivity implements ActionListener {
    @BindView(R.id.toolBar)
    Toolbar toolbar;
    private FragmentManager fragManager;
    private int state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        ButterKnife.bind(this);
        fragManager = getSupportFragmentManager();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        return selector(item);
                    }
                });

        //восстановление данных
        if (savedInstanceState != null) {
            state = savedInstanceState.getInt("key");
            bottomNavigationView.setSelectedItemId(state);
        } else {
            bottomNavigationView.setSelectedItemId(R.id.mSave);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("onResume", "Activity");
    }

    //Сохранение состояния
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("key", state);
    }

    //Добавить меню справа
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    //Обработчик меню выбора цвета
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        View background = null;
        try {
            if (fragManager.findFragmentByTag("save").isVisible()) {
                background = findViewById(R.id.pageBackgroundSave);
            } else if (fragManager.findFragmentByTag("load").isVisible()) {
                background = findViewById(R.id.pageBackgroundLoad);
            } else if (fragManager.findFragmentByTag("pager").isVisible()) {
                switch (((ViewPager) findViewById(R.id.pagerContainer)).getCurrentItem()) {
                    case 0:
                        background = findViewById(R.id.pageBackgroundSave);
                        break;
                    case 1:
                        background = findViewById(R.id.pageBackgroundLoad);
                        break;
                }
            }
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
        FragmentTransaction transaction = fragManager.beginTransaction();
        switch (item.getItemId()) {
            case R.id.mSave:
                transaction.replace(R.id.frameContent, new FragmentSave(), "save");
                break;
            case R.id.mLoad:
                transaction.replace(R.id.frameContent, new FragmentLoad(), "load");
                break;
            case R.id.mNextActivity:
                transaction.replace(R.id.frameContent, new FragmentVPager(), "pager");
                break;
        }
        transaction.addToBackStack(null).commit();
        state = item.getItemId();
        return true;
    }

    //Менять название
    @Override
    public void titleChange(CharSequence title) {
        getSupportActionBar().setTitle(title);
    }
}