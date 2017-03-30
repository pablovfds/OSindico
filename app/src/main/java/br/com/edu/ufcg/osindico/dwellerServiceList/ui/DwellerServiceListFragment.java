package br.com.edu.ufcg.osindico.dwellerServiceList.ui;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.adapters.DwellerServiceAdapter;
import br.com.edu.ufcg.osindico.adapters.ServiceRequestAdapter;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.ServiceRequestResponse;
import br.com.edu.ufcg.osindico.dwellerServiceList.mvp.DwellerServiceListContract;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DwellerServiceListFragment extends Fragment implements DwellerServiceListContract.View {

    @BindView(R.id.rv_dweller_service) RecyclerView rv_dweller_service;
    @BindView(R.id.progressBar) ProgressBar progressBar;

    private DwellerServiceListContract.Presenter presenter;
    private DwellerServiceAdapter adapter;

    public DwellerServiceListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dweller_service_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.setView(this);
        presenter.loadDwellerServiceList(getToken());
    }

    @Override
    public void setServerError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setServiceList(List<ServiceRequestResponse> responseList) {
        adapter = new DwellerServiceAdapter(responseList);
        rv_dweller_service.setAdapter(adapter);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    private String getToken() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("preferencesOSindico",
                Context.MODE_PRIVATE);
        return sharedPreferences.getString(getString(R.string.user_token), null);
    }

}
