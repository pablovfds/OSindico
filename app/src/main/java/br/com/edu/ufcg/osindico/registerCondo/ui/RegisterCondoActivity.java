package br.com.edu.ufcg.osindico.registerCondo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.data.models.CondoDetails;
import br.com.edu.ufcg.osindico.data.services.SyndicService;
import br.com.edu.ufcg.osindico.registerCondo.mvp.RegisterCondoContract;
import br.com.edu.ufcg.osindico.registerCondo.mvp.RegisterCondoPresenterImpl;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterCondoActivity extends AppCompatActivity implements
        RegisterCondoContract.View {

    @BindView(R.id.editTextCondoName) EditText editTextName;

    @BindView(R.id.editTextCondoPhone) EditText editTextPhone;

    @BindView(R.id.editTextNumber) EditText editTextNumber;

    @BindView(R.id.editTextZipCode) EditText editTextZipCode;

    @BindView(R.id.editTextAddress) EditText editTextAddress;

    @BindView(R.id.editTextState) EditText editTextState;

    @BindView(R.id.editTextCity) EditText editTextCity;

    @BindView(R.id.register_condo_progress) ProgressBar progressBar;

    private RegisterCondoContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_condo);

        ButterKnife.bind(this);

        SyndicService service = new SyndicService();

        this.presenter = new RegisterCondoPresenterImpl(service, this);
    }

    @Override protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.condo_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.button_register){

        }

        return super.onOptionsItemSelected(item);
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
    public void setNameError() {
        editTextName.setError(getString(R.string.msg_name_error));
    }

    @Override
    public void setPhoneError() {
        editTextPhone.setError(getString(R.string.msg_phone_error));
    }

    @Override
    public void setAddressError() {
        editTextAddress.setError(getString(R.string.msg_street_error));
    }

    @Override
    public void setNumberError() {
        editTextNumber.setError(getString(R.string.msg_condo_number_error));
    }

    @Override
    public void setCityError() {
        editTextCity.setError(getString(R.string.msg_city_error));
    }

    @Override
    public void setZipCodeError() {
        editTextZipCode.setError(getString(R.string.msg_zipcode_error));
    }

    @Override
    public void setStateError() {
        editTextState.setError(getString(R.string.msg_state_error));
    }

    @Override
    public void navigateToLogin() {

    }

    @Override
    public void navigateToRegisterSyndic(CondoDetails condoDetails) {

    }

    @Override
    public void setServerError() {
        Toast.makeText(this, getString(R.string.msg_server_error), Toast.LENGTH_SHORT).show();
    }
}
