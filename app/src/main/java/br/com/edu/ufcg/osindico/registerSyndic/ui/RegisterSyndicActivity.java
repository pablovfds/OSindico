package br.com.edu.ufcg.osindico.registerSyndic.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.base.BaseActivity;
import br.com.edu.ufcg.osindico.data.services.SyndicService;
import br.com.edu.ufcg.osindico.registerCondo.ui.RegisterCondoActivity;
import br.com.edu.ufcg.osindico.registerSyndic.mvp.RegisterSyndicContract;
import br.com.edu.ufcg.osindico.registerSyndic.mvp.RegisterSyndicPresenterImpl;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterSyndicActivity extends AppCompatActivity implements RegisterSyndicContract.View {

    @BindView(R.id.editTextSyndicName) EditText editTextName;

    @BindView(R.id.editTextEmail) EditText editTextEmail;

    @BindView(R.id.editTextPassword) EditText editTextPassword;

    @BindView(R.id.editTextConfirmPassword) EditText editTextConfirmPassword;

    @BindView(R.id.editTextSyndicPhone) EditText editTextPhone;

    @BindView(R.id.register_syndic_progress) ProgressBar progressBar;

    @BindView(R.id.toolbar_condo) Toolbar toolbar;

    RegisterSyndicContract.Presenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_syndic);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        SyndicService service = new SyndicService();
        presenter = new RegisterSyndicPresenterImpl(service);
        presenter.setView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.syndic_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.button_next){

            editTextName.setError(null);
            editTextEmail.setError(null);
            editTextPassword.setError(null);
            editTextConfirmPassword.setError(null);
            editTextPhone.setError(null);

            String name = editTextName.getText().toString();
            String email = editTextEmail.getText().toString();
            String password = editTextPassword.getText().toString();
            String confirmPassword = editTextConfirmPassword.getText().toString();
            String phone = editTextPhone.getText().toString();

            presenter.validateCredentials(name, email, password, confirmPassword, phone);
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
    public void setEmailError() {
        editTextEmail.setError(getString(R.string.msg_name_error));
    }

    @Override
    public void setPasswordError() {
        editTextPassword.setError(getString(R.string.msg_syndic_password_error));
    }

    @Override
    public void setConfirmPasswordError() {
        editTextConfirmPassword.setError(getString(R.string.msg_syndic_confirm_password_error));
    }

    @Override
    public void setPhoneError() {
        editTextPhone.setError(getString(R.string.msg_phone_error));
    }

    @Override
    public void setServerError(String serverError) {
        Toast.makeText(this, serverError, Toast.LENGTH_LONG).show();
    }

    @Override
    public void navigateToRegisterCondo(Long syndicId) {
        Toast.makeText(this, getString(R.string.msg_registration_success), Toast.LENGTH_SHORT)
                .show();
        Intent intent = new Intent(this, RegisterCondoActivity.class);
        intent.putExtra("syndicId", syndicId);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
