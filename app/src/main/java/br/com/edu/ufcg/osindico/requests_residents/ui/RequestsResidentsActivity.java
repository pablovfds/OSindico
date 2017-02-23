package br.com.edu.ufcg.osindico.requests_residents.ui;

import android.content.Intent;
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
import br.com.edu.ufcg.osindico.adapters.RequestsResidentsAdapter;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.ResidentResponse;
import br.com.edu.ufcg.osindico.data.services.SyndicService;
import br.com.edu.ufcg.osindico.requests_residents.mvp.RequestsResidentsContract;
import br.com.edu.ufcg.osindico.requests_residents.mvp.RequestsResidentsPresenterImpl;
import br.com.edu.ufcg.osindico.residentDetails.ui.ResidentDetailsActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RequestsResidentsActivity extends AppCompatActivity implements
        RequestsResidentsContract.View, ItemClickListener {

    @BindView(R.id.card_recycler_view) RecyclerView recyclerView;

    @BindView(R.id.progressBar) ProgressBar progressBar;

    private RequestsResidentsContract.Presenter presenter;
    private RequestsResidentsAdapter adapter;

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
        presenter.setView(this);
        presenter.loadRequestsResidents("TEydrMm8BkxSQi95FOYoL+p254w0kDtZAIsbS5KzDkktppVFLFIGywBRx++uZHvvjzr8rbLzQ01yhrgBwZcCWQ==");
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
    public void setRequestsResidentsList(List<ResidentResponse> residents) {
       updateAdapter(residents);
       Toast.makeText(this, "Existem " + residents.size() + " solicitações de moradores no momento",
               Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(ResidentResponse residentResponse) {
        Intent i = new Intent(this, ResidentDetailsActivity.class);
        i.putExtra("resident", residentResponse);
        startActivity(i);
    }

    private  void updateAdapter(List<ResidentResponse> residentResponses){
        adapter = new RequestsResidentsAdapter(residentResponses);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }
}
