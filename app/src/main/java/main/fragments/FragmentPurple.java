package main.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.igorr.homework_8.R;

/**
 * Created by Igorr on 01.02.2018.
 */

public class FragmentPurple extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle bundle){
        return inflater.inflate(R.layout.fragment_purple,parent,false);
    }
}
