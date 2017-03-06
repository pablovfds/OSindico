package br.com.edu.ufcg.osindico.homeSyndic.mvp;

import android.content.Context;
import android.content.SharedPreferences;

import br.com.edu.ufcg.osindico.R;


public class HomeSyndicModelImpl implements HomeSyndicContract.Model {

    @Override
    public void logoutUser(Context context, HomeSyndicListener homeSyndicListener) {

        try{
            SharedPreferences preferences = context.getSharedPreferences(context.getString(R.string.preferencesOSindico), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.commit();
            homeSyndicListener.onLogoutSuccess("Successful logout");
        }catch (Exception e){
            homeSyndicListener.onLogoutFail("Logout failed");
        }

    }
}
