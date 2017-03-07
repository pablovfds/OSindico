package br.com.edu.ufcg.osindico.homeDweller.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.List;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.adapters.ResidentMessagesFeedAdapter;
import br.com.edu.ufcg.osindico.base.BaseActivity;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.MessageResponse;
import br.com.edu.ufcg.osindico.data.services.DwellerService;
import br.com.edu.ufcg.osindico.data.services.SyndicService;
import br.com.edu.ufcg.osindico.homeDweller.mvp.HomeDwellerContract;
import br.com.edu.ufcg.osindico.homeDweller.mvp.HomeDwellerPresenterImpl;
import br.com.edu.ufcg.osindico.loginUser.ui.LoginUserActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DwellerHomeActivity extends BaseActivity implements HomeDwellerContract.View{

    private HomeDwellerContract.Presenter presenter;
    private ResidentMessagesFeedAdapter adapter;

//    @BindView(R.id.dweller_name) TextView textViewName;
//    @BindView(R.id.dweller_email) TextView textViewEmail;
    @BindView(R.id.resident_home_recycler_view) RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dweller_home);
        ButterKnife.bind(this);

//        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.preferencesOSindico), Context.MODE_PRIVATE);
//        textViewName.setText(sharedPreferences.getString(getString(R.string.user_name), null));
//        textViewEmail.setText(sharedPreferences.getString(getString(R.string.user_email), null));

        Log.e("on create", "morador");

        DwellerService service = new DwellerService();
        presenter = new HomeDwellerPresenterImpl(service);
        presenter.setView(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        initViews();
    }

    private void initViews(){
        //recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        loadList();
    }

    private void loadList(){

        SharedPreferences sharedpreferences = getSharedPreferences(getString(R.string.preferencesOSindico), Context.MODE_PRIVATE);
        String token = sharedpreferences.getString(getString(R.string.user_token), "");
        Log.e("load list", "load" + token);
        presenter.setView(this);
        presenter.loadMessages(token);
    }

    @Override
    public void setMessagesList(List<MessageResponse> messagesResponses) {
        adapter = new ResidentMessagesFeedAdapter(messagesResponses);
        recyclerView.setAdapter(adapter);
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

    @Override
    public void setServerError(String errorMessage) {

    }
}
