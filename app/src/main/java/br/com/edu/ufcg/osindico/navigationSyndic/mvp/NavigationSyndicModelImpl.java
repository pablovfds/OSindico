package br.com.edu.ufcg.osindico.navigationSyndic.mvp;

import android.content.SharedPreferences;

public class NavigationSyndicModelImpl implements NavigationSyndicContract.Model {
    @Override
    public void logout(SharedPreferences preferences, OnNavigationSyndicListener listener) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
        listener.onSuccessLogout();
    }
}
