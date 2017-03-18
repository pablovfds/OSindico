package br.com.edu.ufcg.osindico.serviceRequestList.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.edu.ufcg.osindico.R;
import butterknife.ButterKnife;

public class ServiceRequestListFragment extends Fragment {


    public ServiceRequestListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_service_request_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

}
