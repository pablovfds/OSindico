package br.com.edu.ufcg.osindico.navigationDweller.mvp;

import android.content.SharedPreferences;

public class NaviagationDwellerModelImpl implements NaviagationDwellerContract.Model {

    @Override
    public void logout(SharedPreferences preferences, OnLogoutFinishedListener listener) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
        listener.onSucess();
    }
}
