package br.com.edu.ufcg.osindico.homeDweller.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.homeDweller.mvp.HomeDwellerContract;
import br.com.edu.ufcg.osindico.homeDweller.mvp.HomeDwellerPresenterImpl;
import br.com.edu.ufcg.osindico.homeSyndic.mvp.HomeSyndicContract;
import br.com.edu.ufcg.osindico.homeSyndic.ui.SyndicHomeActivity;
import br.com.edu.ufcg.osindico.loginUser.ui.LoginUserActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DwellerHomeActivity extends AppCompatActivity  implements HomeDwellerContract.View{

    private HomeDwellerContract.Presenter presenter;

    @BindView(R.id.dweller_name) TextView textViewName;
    @BindView(R.id.dweller_email) TextView textViewEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dweller_home);

        ButterKnife.bind(this);
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.preferencesOSindico), Context.MODE_PRIVATE);

        textViewName.setText(sharedPreferences.getString(getString(R.string.user_name), null));
        textViewEmail.setText(sharedPreferences.getString(getString(R.string.user_email), null));

        presenter = new HomeDwellerPresenterImpl();
        presenter.setView(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_actions_dewller, menu);
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
    public void setSuccessLogout(String logoutResponse) {
        Log.e("Success", logoutResponse);
        startActivity(new Intent(DwellerHomeActivity.this, LoginUserActivity.class));
        this.finish();
    }

    @Override
    public void onBackPressed() {
        return;
    }

    @Override
    public void setFailLogout(String logoutResponse) {
        Log.e("Fail", logoutResponse);
    }
}
