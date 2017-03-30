package br.com.edu.ufcg.osindico.request_service.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.edu.ufcg.osindico.R;
import butterknife.ButterKnife;

/**
 * Created by Cathy on 22/03/2017.
 */

public class RequestSuccessFragment extends Fragment {

    public RequestSuccessFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_send_request, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
