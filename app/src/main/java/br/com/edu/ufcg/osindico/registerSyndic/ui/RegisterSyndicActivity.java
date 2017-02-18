package br.com.edu.ufcg.osindico.registerSyndic.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.registerCondo.ui.RegisterCondoActivity;
import br.com.edu.ufcg.osindico.registerSyndic.mvp.RegisterSyndicContract;
import br.com.edu.ufcg.osindico.registerSyndic.mvp.RegisterSyndicPresenterImpl;
import butterknife.ButterKnife;

public class RegisterSyndicActivity extends AppCompatActivity implements RegisterSyndicContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_syndic);

        ButterKnife.bind(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.syndic_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.button_next){
            RegisterSyndicContract.Presenter presenter = new RegisterSyndicPresenterImpl();
            startActivity(new Intent(this, RegisterCondoActivity.class));
        }

        return super.onOptionsItemSelected(item);
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
    public void navigateToRegisterCondo() {

    }


}
