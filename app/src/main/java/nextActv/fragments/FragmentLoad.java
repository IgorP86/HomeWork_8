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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Igorr on 08.02.2018.
 */

public class FragmentLoad extends Fragment {
    public View view;
    public ActionListener actionListener;
    private Unbinder unbinder;
    @BindView(R.id.pageLoad)
    TextView textView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            actionListener = (ActionListener) context;
        } catch (Exception e) {
            Log.d("Exception", e.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.page_load, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, this.view);
        super.onActivityCreated(savedInstanceState);
        actionListener.titleChange(textView.getText());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}