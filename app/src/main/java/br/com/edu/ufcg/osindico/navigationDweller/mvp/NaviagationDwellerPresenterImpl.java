package br.com.edu.ufcg.osindico.navigationDweller.mvp;

import android.content.SharedPreferences;

import br.com.edu.ufcg.osindico.R;

public class NaviagationDwellerPresenterImpl implements NaviagationDwellerContract.Presenter,
        NaviagationDwellerContract.Model.OnLogoutFinishedListener {

    NaviagationDwellerContract.View view;
    NaviagationDwellerContract.Model model;
    SharedPreferences preferences;

    public NaviagationDwellerPresenterImpl(SharedPreferences preferencesRef) {
        this.preferences = preferencesRef;
        this.model = new NaviagationDwellerModelImpl();
    }

    @Override
    public void setView(NaviagationDwellerContract.View newView) {
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
                model.logout(this.preferences, this);
                break;
        }
    }

}
