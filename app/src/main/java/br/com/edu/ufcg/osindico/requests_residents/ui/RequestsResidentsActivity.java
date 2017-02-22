package br.com.edu.ufcg.osindico.requests_residents.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.data.services.SyndicService;
import br.com.edu.ufcg.osindico.requests_residents.mvp.RequestsResidentsContract;
import br.com.edu.ufcg.osindico.requests_residents.mvp.RequestsResidentsPresenterImpl;
import butterknife.ButterKnife;

public class RequestsResidentsActivity extends AppCompatActivity implements
        RequestsResidentsContract.View {

    private RequestsResidentsContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests_residents);

        ButterKnife.bind(this);

        SyndicService service = new SyndicService();
        presenter = new RequestsResidentsPresenterImpl(service);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.loadRequestsResidents("token");
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setServerError(String errorMessage) {

    }

    @Override
    public void setRequestsResidentsList(List<String> residents) {
        //Resposta da lista
    }
}
