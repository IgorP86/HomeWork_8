package com.igorr.homework_8;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import main.fragments.FragmentPurple;
import main.fragments.FragmentRed;

/**
 * Created by Igorr on 05.02.2018.
 */

public class FragmentVPager extends Fragment {
    private List<Fragment> fragmentList = new ArrayList<>();

    private static int count = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_viewpager, parent, false);
    }

    @Override
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);

        myFragPagerAdapter pagerAdapter = new myFragPagerAdapter(getActivity().getSupportFragmentManager());
        ViewPager viewPager = getActivity().findViewById(R.id.pagerContainer);
        viewPager.setAdapter(pagerAdapter);

        fragmentList.add(new FragmentPurple());
        fragmentList.add(new FragmentRed());
        Log.d(Integer.toString(count++), " - onActivityCreated");
        Log.d("size ", Integer.toString(fragmentList.size()));
    }

    class myFragPagerAdapter extends FragmentStatePagerAdapter {

        public myFragPagerAdapter(FragmentManager fm) {
            super(fm);
            Log.d("adapter ", " called");
        }

        @Override
        public Fragment getItem(int position) {
            Log.d("getItem", Integer.toString(position));
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
