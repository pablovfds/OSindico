package br.com.edu.ufcg.osindico.dwellerRequests.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.Utils.ItemClickListener;
import br.com.edu.ufcg.osindico.adapters.RequestDwellerAdapter;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.DwellerResponse;
import br.com.edu.ufcg.osindico.data.services.SyndicService;
import br.com.edu.ufcg.osindico.dwellerRequests.mvp.RequestsResidentsContract;
import br.com.edu.ufcg.osindico.dwellerRequests.mvp.RequestsResidentsPresenterImpl;
import br.com.edu.ufcg.osindico.dwellerDetails.ui.DwellerDetailsActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RequestsResidentsActivity extends AppCompatActivity implements
        RequestsResidentsContract.View, ItemClickListener {

    @BindView(R.id.card_recycler_view) RecyclerView recyclerView;

    @BindView(R.id.progressBar) ProgressBar progressBar;

    private RequestsResidentsContract.Presenter presenter;
    private RequestDwellerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests_residents);

        ButterKnife.bind(this);

        SyndicService service = new SyndicService();
        presenter = new RequestsResidentsPresenterImpl(service);

        getSupportActionBar().setTitle("Lista de solicitações de moradores");
    }

    @Override
    protected void onStart() {
        super.onStart();
        initViews();
    }

    private void initViews(){
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        loadList();
    }

    private void loadList(){

        SharedPreferences sharedpreferences = getSharedPreferences(getString(R.string.preferencesOSindico), Context.MODE_PRIVATE);
        String token = sharedpreferences.getString(getString(R.string.user_token), "");

        presenter.setView(this);
        presenter.loadRequestsResidents(token);
    }

    @Override
    protected void onDestroy() {
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
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setRequestsResidentsList(List<DwellerResponse> residents) {
       updateAdapter(residents);
       Toast.makeText(this, "Existem " + residents.size() + " solicitações de moradores no momento",
               Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(DwellerResponse dwellerResponse) {
        Intent i = new Intent(this, DwellerDetailsActivity.class);
        i.putExtra("resident", dwellerResponse);
        startActivity(i);
        finish();
    }

    private  void updateAdapter(List<DwellerResponse> dwellerResponses){
        adapter = new RequestDwellerAdapter(dwellerResponses);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }
}
