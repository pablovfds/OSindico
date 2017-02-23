package br.com.edu.ufcg.osindico.residentDetails.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.Utils.ItemClickListener;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.ResidentResponse;
import br.com.edu.ufcg.osindico.data.services.SyndicService;
import br.com.edu.ufcg.osindico.requests_residents.ui.RequestsResidentsActivity;
import br.com.edu.ufcg.osindico.residentDetails.mvp.ResidentDetailsContract;
import br.com.edu.ufcg.osindico.residentDetails.mvp.ResidentDetailsPresenterImpl;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResidentDetailsActivity extends AppCompatActivity implements ResidentDetailsContract.View{

    @BindView(R.id.tv_resident_name) TextView tv_resident_name;
    @BindView(R.id.tv_resident_email) TextView tv_resident_email;
    @BindView(R.id.tv_resident_phone) TextView tv_resident_phone;

    private ResidentDetailsContract.Presenter presenter;
    private ResidentResponse resident;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resident_details);

        ButterKnife.bind(this);

        SyndicService service = new SyndicService();

        presenter = new ResidentDetailsPresenterImpl(service);

        Intent i = getIntent();
        resident = (ResidentResponse) i.getSerializableExtra("resident");

        tv_resident_name.setText(resident.getName());
        tv_resident_email.setText(resident.getEmail());
        tv_resident_phone.setText(resident.getContact().getPhoneNumber());

        getSupportActionBar().setTitle("Perfil do morador");

        //pegar token do sharedpreferences
        token = "TEydrMm8BkxSQi95FOYoL+p254w0kDtZAIsbS5KzDkktppVFLFIGywBRx++uZHvvjzr8rbLzQ01yhrgBwZcCWQ==";
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
    public void navigateToRequestsResidents() {
        Toast.makeText(this, getString(R.string.msg_success_request), Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, RequestsResidentsActivity.class));
    }


}
