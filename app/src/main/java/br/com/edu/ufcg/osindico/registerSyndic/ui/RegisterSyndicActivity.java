package br.com.edu.ufcg.osindico.registerSyndic.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.registerCondo.mvp.RegisterCondoMVPContract;

public class RegisterSyndicActivity extends AppCompatActivity implements RegisterCondoMVPContract.RegisterCondoView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_syndic);
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
