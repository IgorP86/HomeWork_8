package nextActv.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.igorr.homework_8.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Igorr on 05.02.2018.
 */

public class FragmentVPager extends Fragment {
    private List<Fragment> fragmentList = new ArrayList<>();

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

        TabLayout tabLayout = getActivity().findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);

        fragmentList.add(new FragmentSave());
        fragmentList.add(new FragmentLoad());
        Log.d("size", Integer.toString(fragmentList.size()));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentList.clear();
    }

    class myFragPagerAdapter extends FragmentStatePagerAdapter {
        String[] title = {"Сохранить", "Загрузить"};

        public myFragPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }
    }
}