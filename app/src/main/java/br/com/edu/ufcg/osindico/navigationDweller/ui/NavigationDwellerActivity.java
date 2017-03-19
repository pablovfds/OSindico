package br.com.edu.ufcg.osindico.navigationDweller.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.condominium_rules.ui.CondominiumRulesActivity;
import br.com.edu.ufcg.osindico.homeDweller.ui.DwellerMessagesFragment;
import br.com.edu.ufcg.osindico.loginUser.ui.LoginUserActivity;
import br.com.edu.ufcg.osindico.navigationDweller.mvp.NavigationDwellerContract;
import br.com.edu.ufcg.osindico.navigationDweller.mvp.NavigationDwellerPresenterImpl;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NavigationDwellerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, NavigationDwellerContract.View {

    @BindView(R.id.toolbarDweller) Toolbar toolbar;
    @BindView(R.id.drawer_layout_dweller) DrawerLayout drawer;
    @BindView(R.id.nav_view_dweller) NavigationView navigationView;

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
    }

    @Override
    public void navigateToMessageDweller() {
        Toast.makeText(this, getString(R.string.nav_messages), Toast.LENGTH_SHORT).show();
        //setFragment(new DwellerMessagesFragment());
    }

    @Override
    public void navigateToSettingsDweller() {
        Toast.makeText(this, getString(R.string.nav_settings), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToAboutDweller() {
        Toast.makeText(this, getString(R.string.nav_about), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToCalendarDweller() {
        Toast.makeText(this, getString(R.string.nav_calendar), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToCondoDetails() {
        Toast.makeText(this, getString(R.string.nav_my_condo), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, CondominiumRulesActivity.class);
        intent.putExtra("type", "morador");
        startActivity(intent);
    }

    @Override
    public void navigateToLogin() {
        startActivity(new Intent(this, LoginUserActivity.class));
        finish();
    }

    private void setFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
    }
}
