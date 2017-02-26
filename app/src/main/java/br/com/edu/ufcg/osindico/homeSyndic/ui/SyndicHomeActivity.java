package br.com.edu.ufcg.osindico.homeSyndic.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.homeSyndic.mvp.HomeSyndicContract;
import br.com.edu.ufcg.osindico.homeSyndic.mvp.HomeSyndicPresenterImpl;
import br.com.edu.ufcg.osindico.loginUser.ui.LoginUserActivity;
import br.com.edu.ufcg.osindico.dwellerRequests.ui.RequestsDwellersActivity;
import br.com.edu.ufcg.osindico.syndicMessages.ui.SyndicMessageActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SyndicHomeActivity extends AppCompatActivity implements HomeSyndicContract.View {

    private HomeSyndicContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syndic_home);

        ButterKnife.bind(this);

        presenter = new HomeSyndicPresenterImpl();
        presenter.setView(this);
    }

    @OnClick(R.id.btn_openRequests)
    public void openRequestsDweller(){
        startActivity(new Intent(this, RequestsDwellersActivity.class));
    }

    @OnClick(R.id.btn_openRequests)
    public void openSyndicMessage(){
        startActivity(new Intent(this, SyndicMessageActivity.class));
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
