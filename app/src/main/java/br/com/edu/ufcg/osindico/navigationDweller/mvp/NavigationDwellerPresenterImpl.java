package br.com.edu.ufcg.osindico.navigationDweller.mvp;

import android.content.SharedPreferences;

import br.com.edu.ufcg.osindico.R;

public class NavigationDwellerPresenterImpl implements NavigationDwellerContract.Presenter,
        NavigationDwellerContract.Model.OnLogoutFinishedListener {

    private NavigationDwellerContract.View view;
    private NavigationDwellerContract.Model model;
    private SharedPreferences preferences;

    public NavigationDwellerPresenterImpl(SharedPreferences preferencesRef) {
        this.preferences = preferencesRef;
        this.model = new NavigationDwellerModelImpl();
    }

    @Override
    public void setView(NavigationDwellerContract.View newView) {
        if (newView != null){
            this.view = newView;
        }
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }

    @Override
    public void onSucess() {
        if (view != null){
            this.view.navigateToLogin();
        }
    }

    @Override
    public void onItemClicked(int id) {
        switch (id) {
            case R.id.nav_home:
                this.view.navigateToHomeDweller();
                break;
            case R.id.nav_calendar_dweller:
                this.view.navigateToCalendarDweller();
                break;
            case R.id.nav_messages_dweller:
                this.view.navigateToMessageDweller();
                break;
            case R.id.nav_my_condo_dweller:
                this.view.navigateToCondoDetails();
                break;
            case R.id.nav_settings_dweller:
                this.view.navigateToSettingsDweller();
                break;
            case R.id.nav_about_dweller:
                this.view.navigateToAboutDweller();
                break;
            case R.id.nav_logout:
                model.logout(this.preferences, this);
                break;
        }
    }

}
