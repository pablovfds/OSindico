package br.com.edu.ufcg.osindico.dwellerRequests.ui;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.Utils.ItemClickListener;
import br.com.edu.ufcg.osindico.adapters.RequestDwellerAdapter;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.DwellerResponse;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.MessageResponse;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.ServiceRequestResponse;
import br.com.edu.ufcg.osindico.data.services.SyndicService;
import br.com.edu.ufcg.osindico.dwellerDetails.ui.DwellerDetailsActivity;
import br.com.edu.ufcg.osindico.dwellerRequests.mvp.RequestsResidentsContract;
import br.com.edu.ufcg.osindico.dwellerRequests.mvp.RequestsResidentsPresenterImpl;
import butterknife.BindView;
import butterknife.ButterKnife;


public class RequestsDwellersFragment extends Fragment implements
        RequestsResidentsContract.View, ItemClickListener {

    @BindView(R.id.card_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private RequestsResidentsContract.Presenter presenter;
    private RequestDwellerAdapter adapter;

    public RequestsDwellersFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //setContentView(R.layout.activity_requests_residents);
        View view = inflater.inflate(R.layout.activity_requests_residents, container, false);
        ButterKnife.bind(this, view);

        SyndicService service = new SyndicService();
        presenter = new RequestsResidentsPresenterImpl(service);
        loadList();

        //getActivity().getActionBar().setTitle("Lista de solicitações de moradores");

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initViews();
    }

    private void initViews(){
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        loadList();
    }

    private void loadList(){

        SharedPreferences sharedpreferences = getActivity().getSharedPreferences(getString(R.string.preferencesOSindico), Context.MODE_PRIVATE);
        String token = sharedpreferences.getString(getString(R.string.user_token), "");

        presenter.setView(this);
        presenter.loadRequestsResidents(token);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.presenter.onDestroy();
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
    public void setServerError(String errorMessage) {
        Toast.makeText(this.getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setRequestsResidentsList(List<DwellerResponse> residents) {
        updateAdapter(residents);
        Toast.makeText(this.getActivity(), "Existem " + residents.size() + " solicitações de moradores no momento",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(DwellerResponse dwellerResponse) {
        Intent i = new Intent(this.getActivity(), DwellerDetailsActivity.class);
        i.putExtra("resident", dwellerResponse);
        startActivity(i);
    }

    @Override
    public void onClick(MessageResponse messageResponse) {

    }

    @Override
    public void onClick(ServiceRequestResponse serviceRequestResponse) {

    }

    private void updateAdapter(List<DwellerResponse> dwellerResponses){
        adapter = new RequestDwellerAdapter(dwellerResponses);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }
}
