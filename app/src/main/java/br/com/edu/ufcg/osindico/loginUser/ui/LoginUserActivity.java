package br.com.edu.ufcg.osindico.loginUser.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.com.edu.ufcg.osindico.QRCodeReader.ReaderActivity;
import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.LoginResponse;
import br.com.edu.ufcg.osindico.data.services.LoginService;
import br.com.edu.ufcg.osindico.homeDweller.ui.DwellerHomeActivity;
import br.com.edu.ufcg.osindico.homeSyndic.ui.SyndicHomeActivity;
import br.com.edu.ufcg.osindico.loginUser.mvp.LoginUserContract;
import br.com.edu.ufcg.osindico.loginUser.mvp.LoginUserPresenterImpl;
import br.com.edu.ufcg.osindico.registerSyndic.ui.RegisterSyndicActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginUserActivity extends AppCompatActivity implements LoginUserContract.View {
    @BindView(R.id.editTextEmail)
    EditText editTextEmail;
    @BindView(R.id.editTextSenha)
    EditText editTextPassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.btn_cadastrar)
    TextView btnCadastrar;

    private final String MORADOR = "MORADOR";
    private final String SINDICO = "SINDICO";

    private LoginUserContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        LoginService loginService = new LoginService();
        presenter = new LoginUserPresenterImpl(loginService);
        presenter.setView(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @OnClick(R.id.btnLogin)
    public void login(View view) {
        String email = editTextEmail.getText().toString();
        String senha = editTextPassword.getText().toString();

        presenter.validateCredentials(email, senha);
    }

    @OnClick(R.id.btn_cadastrar)
    public void cadastrar() {
        new AlertDialog.Builder(this).setIcon(R.drawable.app_icon).setTitle("Selecione o tipo de conta:").setItems(R.array.user_types, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) { // morador
                    startActivity(new Intent(LoginUserActivity.this, ReaderActivity.class));
                } else if (i == 1) { // sindico
                    startActivity(new Intent(LoginUserActivity.this, RegisterSyndicActivity.class));
                }
            }
        }).show();
    }

    @Override
    public void setEmailError() {
        editTextEmail.setError(getString(R.string.msg_syndic_email_error));
    }

    @Override
    public void setPasswordError() {
        editTextPassword.setError(getString(R.string.msg_syndic_password_error));
    }

    @Override
    public void setSuccessLogin(LoginResponse loginResponse) {
        SharedPreferences sharedpreferences = getApplicationContext().getSharedPreferences(getString(R.string.preferencesOSindico), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putString(getString(R.string.user_token), loginResponse.getToken());
        editor.putString(getString(R.string.user_email), loginResponse.getUsuario().getEmail());
        editor.putString(getString(R.string.user_name), loginResponse.getUsuario().getName());
        editor.putString(getString(R.string.user_type), loginResponse.getUsuario().getTipo());
        editor.commit();

        if (loginResponse.getUsuario().getTipo().equals(MORADOR)) {
            Intent dwellerIntent = new Intent(this, DwellerHomeActivity.class);
            dwellerIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(dwellerIntent);
        } else if (loginResponse.getUsuario().getTipo().equals(SINDICO)) {
            Intent syndicIntent = new Intent(this, SyndicHomeActivity.class);
            syndicIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(syndicIntent);
        }
    }

    @Override
    public void setServerError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }
}

