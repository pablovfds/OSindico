package br.com.edu.ufcg.osindico.navigationDweller.ui;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.condominium_rules.ui.CondominiumRulesActivity;
import br.com.edu.ufcg.osindico.condominium_rules.ui.CondominiumRulesFragment;
import br.com.edu.ufcg.osindico.dwellerRequests.ui.RequestsDwellersFragment;
import br.com.edu.ufcg.osindico.homeDweller.ui.DwellerMessagesFragment;
import br.com.edu.ufcg.osindico.loginUser.ui.LoginUserActivity;
import br.com.edu.ufcg.osindico.navigationDweller.mvp.NavigationDwellerContract;
import br.com.edu.ufcg.osindico.navigationDweller.mvp.NavigationDwellerPresenterImpl;
import br.com.edu.ufcg.osindico.navigationSyndic.ui.NavigationSyndicActivity;
import br.com.edu.ufcg.osindico.request_service.ui.RequestServicesFragment;
import br.com.edu.ufcg.osindico.serviceRequestList.ui.ServiceRequestListFragment;
import br.com.edu.ufcg.osindico.syndicMessages.ui.SyndicMessageFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NavigationDwellerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, NavigationDwellerContract.View {

    @BindView(R.id.toolbarDweller) Toolbar toolbar;
    @BindView(R.id.drawer_layout_dweller) DrawerLayout drawer;
    @BindView(R.id.nav_view_dweller) NavigationView navigationView;
    @BindView(R.id.bottombar_dweller) BottomNavigationView mBottomNav;

    private NavigationDwellerContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_dweller);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.preferencesOSindico), Context.MODE_PRIVATE);

        this.presenter = new NavigationDwellerPresenterImpl(sharedPreferences);
        this.presenter.setView(this);

        setFragment(new DwellerMessagesFragment());
        mBottomNav.setVisibility(View.GONE);

        setBottombarItemSelected();
    }

    private void setBottombarItemSelected(){
        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.tab_messages_dweller:
                        setFragment(new DwellerMessagesFragment());
                        Log.e("mensagens", "mensagens morador");
                        break;
                    case R.id.tab_request_services_dweller:
                        setFragment(new RequestServicesFragment());
                        Toast.makeText(NavigationDwellerActivity.this, "servicos", Toast.LENGTH_SHORT).show();
                        Log.e("servicos", "servicos");
                        break;
                    case R.id.tab_claims_dweller:
                        //setFragment();
                        Toast.makeText(NavigationDwellerActivity.this, "reclamacoes", Toast.LENGTH_SHORT).show();
                        Log.e("reclamacoes", "reclamacoes");
                        break;
                }
                return true;
            }
        });
    }


    private void setFragment(android.app.Fragment newFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.presenter.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        this.presenter.onItemClicked(id);

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void navigateToHomeDweller() {
        Toast.makeText(this, getString(R.string.nav_home), Toast.LENGTH_SHORT).show();
        mBottomNav.setVisibility(View.GONE);
    }

    @Override
    public void navigateToMessageDweller() {
        Toast.makeText(this, getString(R.string.nav_messages), Toast.LENGTH_SHORT).show();
        mBottomNav.setVisibility(View.VISIBLE);
        SyndicMessageFragment frag = new SyndicMessageFragment();
        setFragment(frag);
    }

    @Override
    public void navigateToSettingsDweller() {
        Toast.makeText(this, getString(R.string.nav_settings), Toast.LENGTH_SHORT).show();
        mBottomNav.setVisibility(View.GONE);
    }

    @Override
    public void navigateToAboutDweller() {
        Toast.makeText(this, getString(R.string.nav_about), Toast.LENGTH_SHORT).show();
        mBottomNav.setVisibility(View.GONE);
    }

    @Override
    public void navigateToCalendarDweller() {
        Toast.makeText(this, getString(R.string.nav_calendar), Toast.LENGTH_SHORT).show();
        mBottomNav.setVisibility(View.GONE);
    }

    @Override
    public void navigateToCondoDetails() {
        Toast.makeText(this, getString(R.string.nav_my_condo), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, CondominiumRulesActivity.class);
        intent.putExtra("type", "morador");
        startActivity(intent);
        mBottomNav.setVisibility(View.GONE);
    }

    @Override
    public void navigateToLogin() {
        startActivity(new Intent(this, LoginUserActivity.class));
        mBottomNav.setVisibility(View.GONE);
        finish();
    }
}
