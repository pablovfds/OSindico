package br.com.edu.ufcg.osindico.navigationSyndic.mvp;

import android.content.SharedPreferences;

import br.com.edu.ufcg.osindico.R;

public class NavigationSyndicPresenterImpl implements NavigationSyndicContract.Presenter,
        NavigationSyndicContract.Model.OnNavigationSyndicListener {

    private SharedPreferences preferences;
    private NavigationSyndicContract.View view;
    private NavigationSyndicModelImpl model;

    public NavigationSyndicPresenterImpl(SharedPreferences sharedPreferences) {
        this.preferences = sharedPreferences;
        this.model = new NavigationSyndicModelImpl();
    }

    @Override
    public void onSuccessLogout() {
        if (view != null){
            this.view.navigateToLogin();
        }
    }

    @Override
    public void onItemClicked(int id) {
        if ( view!= null){
            switch (id) {
                case R.id.nav_calendar_syndic:
                    this.view.navigateToSyndicCalendar();
                    break;
                case R.id.nav_messages_syndic:
                    this.view.navigateToSyndicMessages();
                    break;
                case R.id.nav_my_condo_syndic:
                    this.view.navigateToCondoDetails();
                    break;
                case R.id.nav_settings_syndic:
                    this.view.navigateToSettings();
                    break;
                case R.id.nav_about_syndic:
                    this.view.navigateToAbout();
                    break;
                case R.id.nav_logout:
                    model.logout(this.preferences, this);
                    break;
            }
        }
    }

    @Override
    public void setView(NavigationSyndicContract.View view) {
        if (view != null){
            this.view = view;
        }
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }
}
