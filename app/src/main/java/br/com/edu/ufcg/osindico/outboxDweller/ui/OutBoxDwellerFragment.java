package br.com.edu.ufcg.osindico.outboxDweller.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.edu.ufcg.osindico.R;
import butterknife.ButterKnife;


public class OutBoxDwellerFragment extends Fragment {

    public OutBoxDwellerFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.empty_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
