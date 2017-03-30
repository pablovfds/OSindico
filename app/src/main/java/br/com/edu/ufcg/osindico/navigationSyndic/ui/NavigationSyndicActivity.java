package br.com.edu.ufcg.osindico.navigationSyndic.ui;

import android.app.Fragment;
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
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.Utils.UpdateTheme;
import br.com.edu.ufcg.osindico.base.BaseActivity;
import br.com.edu.ufcg.osindico.condominiumResidents.ui.CondominiumResidentsFragment;
import br.com.edu.ufcg.osindico.condominium_rules.ui.CondominiumRulesFragment;
import br.com.edu.ufcg.osindico.dwellerRequests.ui.RequestsDwellersFragment;
import br.com.edu.ufcg.osindico.emptyFragment.EmptyFragment;
import br.com.edu.ufcg.osindico.loginUser.ui.LoginUserActivity;
import br.com.edu.ufcg.osindico.navigationSyndic.mvp.NavigationSyndicContract;
import br.com.edu.ufcg.osindico.navigationSyndic.mvp.NavigationSyndicPresenterImpl;
import br.com.edu.ufcg.osindico.serviceRequestList.ui.ServiceRequestListFragment;
import br.com.edu.ufcg.osindico.syndicMessages.ui.SyndicMessageFragment;
import br.com.edu.ufcg.osindico.visitors_list.ui.AllowedVisitorsListFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NavigationSyndicActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, NavigationSyndicContract.View{

    @BindView(R.id.toolbarSyndic) Toolbar toolbar;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.nav_view) NavigationView navigationView;
    @BindView(R.id.navigation) BottomNavigationView mBottomNav;

    private NavigationSyndicContract.Presenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UpdateTheme.setTheme(getApplicationContext(), 1);
        setContentView(R.layout.activity_navigation_syndic);

        ButterKnife.bind(this);

        toolbar.setTitle("O Sindico");

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        SharedPreferences sharedPreferences = getSharedPreferences(
                getString(R.string.preferencesOSindico), Context.MODE_PRIVATE);

        this.presenter = new NavigationSyndicPresenterImpl(sharedPreferences);
        this.presenter.setView(this);

        setFragment(new RequestsDwellersFragment());

        setBottombarItemSelected();

    }

    private void setBottombarItemSelected(){
        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.tab_new_dweller:
                        setFragment(new RequestsDwellersFragment());
                        getSupportActionBar().setTitle("Novos moradores");
                        break;
                    case R.id.tab_dwellers:
                        setFragment(new CondominiumResidentsFragment());
                        getSupportActionBar().setTitle("Moradores do condomínio");
                        break;
                    case R.id.tab_rules:
                        setFragment(new CondominiumRulesFragment());
                        getSupportActionBar().setTitle("Regras do condomínio");
                        break;
                    case R.id.tab_entrance:
                        setFragment(new AllowedVisitorsListFragment());
                        getSupportActionBar().setTitle("Portaria");
                        break;
                    case R.id.tab_messages:
                        setFragment(new SyndicMessageFragment());
                        getSupportActionBar().setTitle("Mensagens");
                        break;
                    case R.id.tab_request_services:
                        setFragment(new ServiceRequestListFragment());
                        getSupportActionBar().setTitle("Serviços requisitados");
                        break;
                    case R.id.tab_claims:
                        setFragment(new EmptyFragment());
                        getSupportActionBar().setTitle("Reclamações");
                        break;
                }
                return true;
            }
        });
    }

    private void setFragment(Fragment newFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame_syndic, newFragment);
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
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        this.presenter.onItemClicked(id);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void navigateToCondoDetails() {
        Toast.makeText(this, getString(R.string.nav_my_condo), Toast.LENGTH_SHORT).show();
        mBottomNav.getMenu().clear();
        mBottomNav.inflateMenu(R.menu.bottom_navigation_items);
        mBottomNav.setVisibility(View.VISIBLE);
        setFragment(new RequestsDwellersFragment());
        getSupportActionBar().setTitle("Meu condomínio");
    }

    @Override
    public void navigateToSyndicMessages() {
        Toast.makeText(this, getString(R.string.nav_messages), Toast.LENGTH_SHORT).show();

        mBottomNav.getMenu().clear();
        mBottomNav.inflateMenu(R.menu.menu_messages);
        mBottomNav.setVisibility(View.VISIBLE);
        SyndicMessageFragment frag = new SyndicMessageFragment();
        getSupportActionBar().setTitle("Mensagens");
        setFragment(frag);
    }

    @Override
    public void navigateToSyndicCalendar() {
        mBottomNav.setVisibility(View.GONE);
        EmptyFragment emptyFragment = new EmptyFragment();
        setFragment(emptyFragment);
        getSupportActionBar().setTitle("Calendário");
        //Toast.makeText(this, getString(R.string.nav_calendar), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToSettings() {
        mBottomNav.setVisibility(View.GONE);
        setFragment(new EmptyFragment());
        getSupportActionBar().setTitle("Configurações");
        //Toast.makeText(this, getString(R.string.nav_settings), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToAbout() {
        mBottomNav.setVisibility(View.GONE);
        setFragment(new EmptyFragment());
        getSupportActionBar().setTitle("Sobre");
        //Toast.makeText(this, getString(R.string.nav_about), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToLogin() {
        mBottomNav.setVisibility(View.GONE);
        setFragment(new EmptyFragment());
        startActivity(new Intent(this, LoginUserActivity.class));
        finish();
    }
}
