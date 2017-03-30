package br.com.edu.ufcg.osindico.navigationDweller.ui;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.allow_visitors.ui.AllowVisitorsFragment;
import br.com.edu.ufcg.osindico.base.BaseActivity;
import br.com.edu.ufcg.osindico.condominium_rules.ui.CondominiumRulesFragment;
import br.com.edu.ufcg.osindico.emptyFragment.EmptyFragment;
import br.com.edu.ufcg.osindico.homeDweller.ui.DwellerMessagesFragment;
import br.com.edu.ufcg.osindico.loginUser.ui.LoginUserActivity;
import br.com.edu.ufcg.osindico.navigationDweller.mvp.NavigationDwellerContract;
import br.com.edu.ufcg.osindico.navigationDweller.mvp.NavigationDwellerPresenterImpl;
import br.com.edu.ufcg.osindico.outboxDweller.ui.OutBoxDwellerFragment;
import br.com.edu.ufcg.osindico.request_service.ui.RequestServicesFragment;
import br.com.edu.ufcg.osindico.serviceListRequestedDweller.ui.MyServicesFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NavigationDwellerActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, NavigationDwellerContract.View {

    @BindView(R.id.toolbarDweller) Toolbar toolbar;
    @BindView(R.id.drawer_layout_dweller) DrawerLayout drawer;
    @BindView(R.id.nav_view_dweller) NavigationView navigationView;
    @BindView(R.id.bottombar_dweller) BottomNavigationView mBottomNav;

    private NavigationDwellerContract.Presenter presenter;
    private Boolean changeMenuOnback;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_dweller);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Página inicial");

        changeMenuOnback = false;

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.preferencesOSindico), Context.MODE_PRIVATE);

        this.presenter = new NavigationDwellerPresenterImpl(sharedPreferences);
        this.presenter.setView(this);

        setFragment(new DwellerMessagesFragment(), "messages");
        mBottomNav.setVisibility(View.GONE);
        setBottombarItemSelected();
    }

    private void setBottombarItemSelected(){
        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.tab_messages_dweller:
                        changeMenuOnback = true;
                        mBottomNav.getMenu().clear();
                        mBottomNav.inflateMenu(R.menu.sub_menu_messages_dweller);
                        setFragment(new DwellerMessagesFragment(), "messages");
                        getSupportActionBar().setTitle("Caixa de entrada");
                        break;
                    case R.id.sub_menu_inbox:
                        changeMenuOnback = true;
                        setFragment(new DwellerMessagesFragment(), "inbox");
                        getSupportActionBar().setTitle("Caixa de entrada");
                        break;
                    case R.id.sub_menu_outbox:
                        changeMenuOnback = true;
                        setFragment(new OutBoxDwellerFragment(), "outbox");
                        getSupportActionBar().setTitle("Caixa de saída");
                        break;
                    case R.id.tab_request_services_dweller:
                        changeMenuOnback = true;
                        mBottomNav.getMenu().clear();
                        mBottomNav.inflateMenu(R.menu.submenu_services_dweller);
                        setFragment(new RequestServicesFragment(), "request_service");
                        getSupportActionBar().setTitle("Solicitar serviço");
                        break;
                    case R.id.sub_menu_request_service:
                        changeMenuOnback = true;
                        setFragment(new RequestServicesFragment(), "request_service");
                        getSupportActionBar().setTitle("Solicitar serviço");
                        break;
                    case R.id.sub_menu_my_requests:
                        changeMenuOnback = true;
                        setFragment(new MyServicesFragment(), "request_service");
                        getSupportActionBar().setTitle("Serviços solicitados");
                        break;
                    case R.id.tab_claims_dweller:
                        changeMenuOnback = false;
                        setFragment(new EmptyFragment(), "empty");
                        getSupportActionBar().setTitle("Reclamações");
                        break;
                    case R.id.tab_lobby:
                        changeMenuOnback = false;
                        setFragment(new AllowVisitorsFragment(), "lobby");
                        getSupportActionBar().setTitle("Portaria");
                        break;
                    case R.id.tab_condo_rules:
                        changeMenuOnback = false;
                        setFragment(new CondominiumRulesFragment(), "condo_rules");
                        getSupportActionBar().setTitle("Regras do condomínio");
                        break;
                }
                return true;
            }
        });
    }


    private void setFragment(android.app.Fragment newFragment, String tag) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, newFragment, tag);
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
        Log.e("change menu", "change" + changeMenuOnback);
        if(changeMenuOnback){
            Log.e("change menu", "change");
            mBottomNav.getMenu().clear();
            mBottomNav.inflateMenu(R.menu.menu_messages_dweller);
            getSupportActionBar().setTitle("O síndico");
            changeMenuOnback = false;
        }else{
            this.finish();
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
        changeMenuOnback = false;
        mBottomNav.setVisibility(View.GONE);
        setFragment(new DwellerMessagesFragment(), "home");
        getSupportActionBar().setTitle("Página inicial");
    }

    @Override
    public void navigateToMessageDweller() {
        changeMenuOnback = false;
        mBottomNav.getMenu().clear();
        mBottomNav.inflateMenu(R.menu.menu_messages_dweller);
        mBottomNav.setVisibility(View.VISIBLE);
        setFragment(new DwellerMessagesFragment(), "messages");
        getSupportActionBar().setTitle("Mensagens");
    }

    @Override
    public void navigateToSettingsDweller() {
        changeMenuOnback = false;
        mBottomNav.setVisibility(View.GONE);
        setFragment(new EmptyFragment(), "empty");
        getSupportActionBar().setTitle("Configurações");
    }

    @Override
    public void navigateToAboutDweller() {
        changeMenuOnback = false;
        mBottomNav.setVisibility(View.GONE);
        setFragment(new EmptyFragment(), "empty");
        getSupportActionBar().setTitle("Sobre");
    }

    @Override
    public void navigateToCalendarDweller() {
        changeMenuOnback = false;
        mBottomNav.setVisibility(View.GONE);
        setFragment(new EmptyFragment(), "empty");
        getSupportActionBar().setTitle("Calendário");
    }

    @Override
    public void navigateToCondoDetails() {
        changeMenuOnback = false;
        mBottomNav.getMenu().clear();
        mBottomNav.inflateMenu(R.menu.menu_dweller_condominium);
        mBottomNav.setVisibility(View.VISIBLE);
        setFragment(new AllowVisitorsFragment(), "allow_entrance");
        getSupportActionBar().setTitle("Condomínio");
    }

    @Override
    public void navigateToLogin() {
        changeMenuOnback = false;
        startActivity(new Intent(this, LoginUserActivity.class));
        mBottomNav.setVisibility(View.GONE);
        finish();
    }

}
