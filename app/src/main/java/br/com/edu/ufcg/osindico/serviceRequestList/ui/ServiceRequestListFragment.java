package br.com.edu.ufcg.osindico.serviceRequestList.ui;


import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.Utils.ItemClickListener;
import br.com.edu.ufcg.osindico.adapters.CondominiumRulesAdapter;
import br.com.edu.ufcg.osindico.adapters.ServiceRequestAdapter;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.DwellerResponse;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.MessageResponse;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.ServiceRequestResponse;
import br.com.edu.ufcg.osindico.data.services.SyndicService;
import br.com.edu.ufcg.osindico.serviceRequestList.mvp.ServiceRequestListContract;
import br.com.edu.ufcg.osindico.serviceRequestList.mvp.ServiceRequestListPresenter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ServiceRequestListFragment extends Fragment implements ServiceRequestListContract.View
        , ItemClickListener {

    @BindView(R.id.service_request_list_recycler_view) RecyclerView recyclerView;

    @BindView(R.id.service_request_progressBar) ProgressBar progressBar;

    private ServiceRequestListContract.Presenter presenter;
    private ServiceRequestAdapter adapter;

    public ServiceRequestListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SyndicService service = new SyndicService();
        presenter = new ServiceRequestListPresenter(service);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_service_request_list, container, true);
        ButterKnife.bind(this, view);

        SyndicService service = new SyndicService();
        presenter = new ServiceRequestListPresenter(service);
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
        presenter.setView(this);
        presenter.loadRequestList(getToken());
    }

    private String getToken(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("preferencesOSindico",
                Context.MODE_PRIVATE);
        return sharedPreferences.getString(getString(R.string.user_token), null);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setRequestList(List<ServiceRequestResponse> requestList) {
        adapter = new ServiceRequestAdapter(requestList);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setTokenError() {
        Toast.makeText(getActivity(), getString(R.string.msg_token_error), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setServerError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setUpdateStatusSuccess(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(DwellerResponse dwellerResponse) {

    }

    @Override
    public void onClick(MessageResponse messageResponse) {

    }

    @Override
    public void onClick(final ServiceRequestResponse serviceRequestResponse) {

        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
        builder1.setMessage(getString(R.string.label_update_service_status));
        builder1.setCancelable(true);

        builder1.setPositiveButton(getString(R.string.label_yes),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        presenter.updateServiceRequestStatus(getToken(), serviceRequestResponse.getId());
                    }
                });

        builder1.setNegativeButton(getString(R.string.label_no),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}
