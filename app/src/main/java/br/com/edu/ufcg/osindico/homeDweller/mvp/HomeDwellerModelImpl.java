package br.com.edu.ufcg.osindico.homeDweller.mvp;

import android.content.Context;
import android.content.SharedPreferences;

import br.com.edu.ufcg.osindico.R;
import br.com.edu.ufcg.osindico.homeSyndic.mvp.HomeSyndicContract;

/**
 * Created by Lucio on 22/02/2017.
 */

public class HomeDwellerModelImpl implements HomeDwellerContract.Model {

    @Override
    public void logoutUser(Context context, HomeDwellerContract.Model.HomeDwellerListener homeDwellerListener) {

        try{
            SharedPreferences preferences = context.getSharedPreferences(context.getString(R.string.preferencesOSindico), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.commit();
            homeDwellerListener.onLogoutSuccess("Successful logout");
        }catch (Exception e){
            homeDwellerListener.onLogoutFail("Logout failed");
        }

    }
}
