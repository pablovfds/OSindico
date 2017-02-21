package br.com.edu.ufcg.osindico.loginUser.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.data.services.LoginService;
import br.com.edu.ufcg.osindico.loginUser.mvp.LoginUserContract;
import br.com.edu.ufcg.osindico.loginUser.mvp.LoginUserPresenterImpl;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginUserActivity extends AppCompatActivity implements LoginUserContract.View{
    @BindView(R.id.editTextEmail) EditText editTextEmail;
    @BindView(R.id.editTextSenha) EditText editTextPassword;

    LoginUserContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        LoginService service = new LoginService();
//        presenter = new LoginUserPresenterImpl(service);
        presenter.setView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }



    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setEmailError() {

    }

    @Override
    public void setPasswordError() {

    }

    @Override
    public void setServerError(String errorMessage) {

    }
}


