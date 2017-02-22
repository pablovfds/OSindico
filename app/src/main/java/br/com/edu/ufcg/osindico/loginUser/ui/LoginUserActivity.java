package br.com.edu.ufcg.osindico.loginUser.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.LoginResponse;
import br.com.edu.ufcg.osindico.data.services.LoginService;
import br.com.edu.ufcg.osindico.loginUser.mvp.LoginUserContract;
import br.com.edu.ufcg.osindico.loginUser.mvp.LoginUserPresenterImpl;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginUserActivity extends AppCompatActivity implements LoginUserContract.View{
    @BindView(R.id.editTextEmail) EditText editTextEmail;
    @BindView(R.id.editTextSenha) EditText editTextPassword;
    @BindView(R.id.btnLogin) Button btnLogin;;

    LoginUserContract.Presenter presenter;

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
    protected void onStart() {
        super.onStart();
        LoginService service = new LoginService();
        presenter = new LoginUserPresenterImpl(service);
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

        presenter.validateCredentials(email,senha);
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
        SharedPreferences sharedpreferences = getSharedPreferences("preferencesOSindico", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putString("token", loginResponse.getToken());
        editor.putString("email", loginResponse.getUsuario().getEmail());
        editor.putString("name", loginResponse.getUsuario().getName());
        editor.putString("tipo", loginResponse.getUsuario().getTipo());
        editor.commit();

        if(loginResponse.getUsuario().getTipo().equals("MORADOR")) {
            Toast.makeText(this, loginResponse.getUsuario().getTipo(), Toast.LENGTH_LONG).show();
        }else if(loginResponse.getUsuario().getTipo().equals("SINDICO")){
            Toast.makeText(this, loginResponse.getUsuario().getTipo(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void setServerError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }
}


