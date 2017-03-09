package br.com.edu.ufcg.osindico.dwellerDetails.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.DwellerResponse;
import br.com.edu.ufcg.osindico.data.services.SyndicService;
import br.com.edu.ufcg.osindico.dwellerRequests.ui.RequestsDwellersActivity;
import br.com.edu.ufcg.osindico.dwellerDetails.mvp.DwellerDetailsContract;
import br.com.edu.ufcg.osindico.dwellerDetails.mvp.DwellerDetailsPresenterImpl;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DwellerDetailsActivity extends AppCompatActivity implements DwellerDetailsContract.View{

    @BindView(R.id.tv_resident_name) TextView tv_resident_name;
    @BindView(R.id.tv_resident_email) TextView tv_resident_email;
    @BindView(R.id.tv_resident_phone) TextView tv_resident_phone;

    private DwellerDetailsContract.Presenter presenter;
    private DwellerResponse resident;
    private String token;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resident_details);

        ButterKnife.bind(this);

        SyndicService service = new SyndicService();

        presenter = new DwellerDetailsPresenterImpl(service);

        Intent i = getIntent();
        resident = (DwellerResponse) i.getSerializableExtra("resident");

        tv_resident_name.setText(resident.getName());
        tv_resident_email.setText(resident.getEmail());
        tv_resident_phone.setText(resident.getContact().getPhoneNumber());

        getSupportActionBar().setTitle("Perfil do morador");

        SharedPreferences sharedpreferences = getSharedPreferences(getString(R.string.preferencesOSindico), Context.MODE_PRIVATE);
        token = sharedpreferences.getString(getString(R.string.user_token), "");

    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.setView(this);
    }

    @OnClick(R.id.btn_accept)
    public void acceptRequest(){ presenter.sendResponseRequest(token, resident.getId(), true);}

    @OnClick(R.id.btn_refuse)
    public void refuseRequest(){
        presenter.sendResponseRequest(token, resident.getId(), false);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setServerError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToRequestsDweller() {
        Toast.makeText(this, getString(R.string.msg_success_request), Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, RequestsDwellersActivity.class));
        finish();
    }
}
