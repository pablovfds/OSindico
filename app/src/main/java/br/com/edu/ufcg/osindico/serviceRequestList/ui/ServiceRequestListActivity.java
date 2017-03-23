package br.com.edu.ufcg.osindico.serviceRequestList.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import java.util.List;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.adapters.ServiceRequestAdapter;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.ServiceRequestResponse;
import br.com.edu.ufcg.osindico.data.services.SyndicService;
import br.com.edu.ufcg.osindico.serviceRequestList.mvp.ServiceRequestListContract;
import br.com.edu.ufcg.osindico.serviceRequestList.mvp.ServiceRequestListPresenter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ServiceRequestListActivity extends AppCompatActivity implements ServiceRequestListContract.View{

    @BindView(R.id.service_request_list_recycler_view) RecyclerView recyclerView;

    @BindView(R.id.service_request_progressBar) ProgressBar progressBar;

    private ServiceRequestListContract.Presenter presenter;
    private ServiceRequestAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_request_list);

        ButterKnife.bind(this);
        SyndicService service = new SyndicService();
        presenter = new ServiceRequestListPresenter(service);
        presenter.setView(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        initializeViews();
    }

    private void initializeViews() {
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        loadServicesRequests();
    }

    private void loadServicesRequests() {
        SharedPreferences sharedPreferences = getSharedPreferences("preferencesOSindico",
                Context.MODE_PRIVATE);
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

    @Override
    public void setUpdateStatusSuccess(String message) {

    }
}
