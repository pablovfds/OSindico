package br.com.edu.ufcg.osindico.serviceRequestList.ui;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.adapters.CondominiumRulesAdapter;
import br.com.edu.ufcg.osindico.adapters.ServiceRequestAdapter;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.ServiceRequestResponse;
import br.com.edu.ufcg.osindico.data.services.SyndicService;
import br.com.edu.ufcg.osindico.serviceRequestList.mvp.ServiceRequestListContract;
import br.com.edu.ufcg.osindico.serviceRequestList.mvp.ServiceRequestListPresenter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ServiceRequestListFragment extends Fragment implements ServiceRequestListContract.View {

    @BindView(R.id.rules_recycler_view) RecyclerView recyclerView;

    @BindView(R.id.rules_progressBar) ProgressBar progressBar;

    private ServiceRequestListContract.Presenter presenter;
    private ServiceRequestAdapter adapter;

    public ServiceRequestListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SyndicService service = new SyndicService();
        presenter = new ServiceRequestListPresenter(service);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_service_request_list, container, false);
        ButterKnife.bind(this, view);
        presenter.setView(this);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initializeViews();
    }

    private void initializeViews() {
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        loadServicesRequests();
    }

    private void loadServicesRequests() {
        SharedPreferences sharedPreferences = getActivity()
                .getSharedPreferences("preferencesOSindico", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString(getString(R.string.user_token), null);

        presenter.setView(this);
        presenter.loadRequestList(token);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setRequestList(List<ServiceRequestResponse> requestList) {
        adapter = new ServiceRequestAdapter(requestList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setTokenError() {

    }

    @Override
    public void setServerError(String message) {

    }
}
