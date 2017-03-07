package br.com.edu.ufcg.osindico.homeSyndic.mvp;

import android.content.Context;
import android.util.Log;

import java.util.List;

import br.com.edu.ufcg.osindico.data.models.ServerResponse.LoginResponse;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.MessageResponse;
import br.com.edu.ufcg.osindico.data.services.SyndicService;
import br.com.edu.ufcg.osindico.homeSyndic.ui.SyndicHomeActivity;


public class HomeSyndicPresenterImpl implements HomeSyndicContract.Presenter, HomeSyndicContract.Model.HomeSyndicListener {

    private HomeSyndicContract.View homeSyndicView;
    private HomeSyndicContract.Model homeSyndicModel;

    public HomeSyndicPresenterImpl(SyndicService service){
        this.homeSyndicModel = new HomeSyndicModelImpl(service);
    }

    @Override
    public void logout(Context context) {
        if(context != null){
            homeSyndicModel.logoutUser(context, this);
        }
    }

    @Override
    public void setView(HomeSyndicContract.View view) {
        Log.e("set view", "");
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
    public void onDestroy() {}

    @Override
    public void onServerError(String message) {}
}
