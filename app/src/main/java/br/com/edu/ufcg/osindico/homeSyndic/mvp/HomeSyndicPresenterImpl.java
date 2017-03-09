package br.com.edu.ufcg.osindico.homeSyndic.mvp;

import android.content.Context;


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
