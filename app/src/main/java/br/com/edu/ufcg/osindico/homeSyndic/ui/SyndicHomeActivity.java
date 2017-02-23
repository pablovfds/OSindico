package br.com.edu.ufcg.osindico.homeSyndic.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.zip.Inflater;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.homeSyndic.mvp.HomeSyndicContract;
import br.com.edu.ufcg.osindico.homeSyndic.mvp.HomeSyndicPresenterImpl;
import br.com.edu.ufcg.osindico.loginUser.mvp.LoginUserPresenterImpl;
import br.com.edu.ufcg.osindico.loginUser.ui.LoginUserActivity;

public class SyndicHomeActivity extends AppCompatActivity implements HomeSyndicContract.View {

    private HomeSyndicContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syndic_home);

        presenter = new HomeSyndicPresenterImpl();
        presenter.setView(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.button_logout:
                presenter.logout(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        return;
    }

    @Override
    public void setSuccessLogout(String logoutResponse) {
        Log.e("Success", logoutResponse);
        startActivity(new Intent(SyndicHomeActivity.this, LoginUserActivity.class));
        this.finish();
    }

    @Override
    public void setFailLogout(String logoutResponse) {
        Log.e("Fail", logoutResponse);
    }
}
