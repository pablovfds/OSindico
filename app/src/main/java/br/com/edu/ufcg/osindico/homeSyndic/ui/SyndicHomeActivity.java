package br.com.edu.ufcg.osindico.homeSyndic.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.zip.Inflater;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.base.BaseActivity;
import br.com.edu.ufcg.osindico.homeSyndic.mvp.HomeSyndicContract;
import br.com.edu.ufcg.osindico.homeSyndic.mvp.HomeSyndicPresenterImpl;
import br.com.edu.ufcg.osindico.loginUser.mvp.LoginUserPresenterImpl;
import br.com.edu.ufcg.osindico.loginUser.ui.LoginUserActivity;
import br.com.edu.ufcg.osindico.requests_residents.ui.RequestsResidentsActivity;

public class SyndicHomeActivity extends BaseActivity implements HomeSyndicContract.View {

    private HomeSyndicContract.Presenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syndic_home);

        presenter = new HomeSyndicPresenterImpl();
        presenter.setView(this);

        Button btn = (Button) findViewById(R.id.btn_openRequests);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SyndicHomeActivity.this, RequestsResidentsActivity.class));
            }
        });

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId){
                    case  R.id.tab_new_dweller:
                        Toast.makeText(getApplicationContext(), "Novo morador", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.tab_dwellers:
                        Toast.makeText(getApplicationContext(), "Lista de moradores", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.tab_rules:
                        Toast.makeText(getApplicationContext(), "Regras do condominio", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });
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
