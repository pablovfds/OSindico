package br.com.edu.ufcg.osindico.allow_visitors.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.edu.ufcg.osindico.R;
import butterknife.ButterKnife;


public class AllowVisitorsSuccessFragment extends Fragment {

    public AllowVisitorsSuccessFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.allowed_visitor_success_fragment, container, false);
        ButterKnife.bind(this, view);

        return view;
    }
}
