package nextActv.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.igorr.homework_8.R;

/**
 * Created by Igorr on 08.02.2018.
 */

public class FragmentSave extends Fragment {
    public View view;
    private TitleChangeListener titleChangeListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            titleChangeListener = (TitleChangeListener) context;
        } catch (Exception e) {
            Log.d("Exception", e.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.page_save, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView tv = getActivity().findViewById(R.id.pageSave);
        CharSequence ch = tv.getText();
        titleChangeListener.titleChange(ch);
      //  titleChangeListener.titleChange(((TextView) getActivity().findViewById(R.id.pageSave)).getText());
    }
}
