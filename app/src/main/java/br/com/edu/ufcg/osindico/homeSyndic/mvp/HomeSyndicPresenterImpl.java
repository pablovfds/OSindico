package br.com.edu.ufcg.osindico.homeSyndic.mvp;

import android.content.Context;
import android.util.Log;

import br.com.edu.ufcg.osindico.data.models.ServerResponse.LoginResponse;
import br.com.edu.ufcg.osindico.homeSyndic.ui.SyndicHomeActivity;

/**
 * Created by Lucio on 22/02/2017.
 */

public class HomeSyndicPresenterImpl implements HomeSyndicContract.Presenter, HomeSyndicContract.Model.HomeSyndicListener {

    private HomeSyndicContract.View homeSyndicView;
    private HomeSyndicContract.Model homeSyndicModel;

    public HomeSyndicPresenterImpl(){
        this.homeSyndicModel = new HomeSyndicModelImpl();
    }

    @Override
    public void logout(Context context) {
        if(context != null){
            homeSyndicModel.logoutUser(context, this);
        }
    }

    @Override
    public void setView(HomeSyndicContract.View view) {
        this.homeSyndicView = view;
    }

    @Override
    public void onLogoutSuccess(String logoutResponse) {
        this.homeSyndicView.setSuccessLogout(logoutResponse);
    }

    @Override
    public void onLogoutFail(String logoutResponse) {
        this.homeSyndicView.setFailLogout(logoutResponse);
    }

    @Override
    public void onServerError(String message) {

    }
}
