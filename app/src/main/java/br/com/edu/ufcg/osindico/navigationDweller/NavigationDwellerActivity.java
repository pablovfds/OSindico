package br.com.edu.ufcg.osindico.navigationDweller;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import br.com.edu.ufcg.osindico.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NavigationDwellerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbarDweller) Toolbar toolbar;
    @BindView(R.id.drawer_layout_dweller) DrawerLayout drawer;
    @BindView(R.id.nav_view_dweller) NavigationView navigationView;

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

        switch (id) {
            case R.id.nav_home:

                break;
            case R.id.nav_calendar_dweller:

                break;
            case R.id.nav_messages_dweller:

                break;
            case R.id.nav_my_condo_dweller:

                break;
            case R.id.nav_settings_dweller:

                break;
            case R.id.nav_about_dweller:

                break;
            case R.id.nav_logout:

                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
