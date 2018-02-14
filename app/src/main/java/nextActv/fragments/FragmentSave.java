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

public class FragmentSave extends Fragment {
    private View view;
    private ActionListener actionListener;
    private Unbinder unbinder;
    @BindView(R.id.pageSave)
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
        view = inflater.inflate(R.layout.page_save, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, this.view);
        super.onActivityCreated(savedInstanceState);
        actionListener.titleChange(textView.getText());
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("onResume","Save");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}