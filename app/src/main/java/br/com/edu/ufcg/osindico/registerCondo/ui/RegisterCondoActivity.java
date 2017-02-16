package br.com.edu.ufcg.osindico.registerCondo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.data.services.SyndicService;
import br.com.edu.ufcg.osindico.registerCondo.mvp.RegisterCondoMVPContract;
import br.com.edu.ufcg.osindico.registerCondo.mvp.RegisterCondoPresenterImpl;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterCondoActivity extends AppCompatActivity implements
        RegisterCondoMVPContract.RegisterCondoView {

    @BindView(R.id.editTextCondoName) EditText editTextName;

    @BindView(R.id.editTextPhone) EditText editTextPhone;

    @BindView(R.id.editTextStreet) EditText editTextStreet;

    @BindView(R.id.editTextNumber) EditText editTextNumber;

    @BindView(R.id.editTextZipCode) EditText editTextZipCode;

    @BindView(R.id.editTextCountry) EditText editTextCountry;

    @BindView(R.id.editTextState) EditText editTextState;

    @BindView(R.id.editTextNeighborhood) EditText editTextNeighborhood;

    @BindView(R.id.editTextCity) EditText editTextCity;

    private RegisterCondoMVPContract.RegisterCondoPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_condo);

        ButterKnife.bind(this);

        SyndicService service = new SyndicService();

        this.presenter = new RegisterCondoPresenterImpl(service);

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setUsernameError() {

    }

    @Override
    public void setPasswordError() {

    }

    @Override
    public void navigateToHome() {

    }
}
