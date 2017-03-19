package br.com.edu.ufcg.osindico.navigationSyndic.ui;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;

import android.app.Fragment;
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
import br.com.edu.ufcg.osindico.Utils.UpdateTheme;
import br.com.edu.ufcg.osindico.base.BaseActivity;
import br.com.edu.ufcg.osindico.dwellerRequests.ui.RequestsDwellersFragment;
import br.com.edu.ufcg.osindico.homeSyndic.ui.SyndicHomeFragment;
import br.com.edu.ufcg.osindico.loginUser.ui.LoginUserActivity;
import br.com.edu.ufcg.osindico.navigationSyndic.mvp.NavigationSyndicContract;
import br.com.edu.ufcg.osindico.navigationSyndic.mvp.NavigationSyndicPresenterImpl;
import br.com.edu.ufcg.osindico.syndicMessages.ui.SyndicMessageFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NavigationSyndicActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, NavigationSyndicContract.View{

    @BindView(R.id.toolbarSyndic) Toolbar toolbar;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.nav_view) NavigationView navigationView;
    BottomNavigationView mBottomNav;
    FragmentManager fragmentManager = getSupportFragmentManager();

    private NavigationSyndicContract.Presenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UpdateTheme.setTheme(getApplicationContext(), 1);
        setContentView(R.layout.activity_navigation_syndic);

        ButterKnife.bind(this);

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

        mBottomNav =  (BottomNavigationView) findViewById(R.id.navigation);



        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.tab_new_dweller:
                        setFragment(new RequestsDwellersFragment());
                        Log.e("novo morador", "novo morador");
                        break;
                    case R.id.tab_dwellers:
                        //setFragment();
                        Toast.makeText(NavigationSyndicActivity.this, "rlista de moradores", Toast.LENGTH_SHORT).show();
                        Log.e("lista", "lista moradores");
                        break;
                    case R.id.tab_rules:
                        //setFragment();
                        Toast.makeText(NavigationSyndicActivity.this, "regras", Toast.LENGTH_SHORT).show();
                        Log.e("regras", "lista regras");
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
    }

    @Override
    public void navigateToSyndicMessages() {
        Toast.makeText(this, getString(R.string.nav_messages), Toast.LENGTH_SHORT).show();

        mBottomNav.getMenu().clear();
        mBottomNav.inflateMenu(R.menu.menu_messages);
        mBottomNav.setVisibility(View.VISIBLE);
        setFragment(new SyndicMessageFragment());
    }

    @Override
    public void navigateToSyndicCalendar() {
        mBottomNav.setVisibility(View.GONE);
        Toast.makeText(this, getString(R.string.nav_calendar), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToSettings() {
        mBottomNav.setVisibility(View.GONE);
        Toast.makeText(this, getString(R.string.nav_settings), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToAbout() {
        mBottomNav.setVisibility(View.GONE);
        Toast.makeText(this, getString(R.string.nav_about), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToLogin() {
        mBottomNav.setVisibility(View.GONE);
        startActivity(new Intent(this, LoginUserActivity.class));
        finish();
    }
}
