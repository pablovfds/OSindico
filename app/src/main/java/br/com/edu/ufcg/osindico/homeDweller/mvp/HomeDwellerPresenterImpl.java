package br.com.edu.ufcg.osindico.homeDweller.mvp;


import android.content.Context;


/**
 * Created by Lucio on 22/02/2017.
 */

public class HomeDwellerPresenterImpl implements HomeDwellerContract.Presenter, HomeDwellerContract.Model.HomeDwellerListener {

    private HomeDwellerContract.View homeDwellerView;
    private HomeDwellerContract.Model homeDwellerModel;

    public HomeDwellerPresenterImpl(){

        this.homeDwellerModel = new HomeDwellerModelImpl();
    }


    @Override
    public void logout(Context context) {

        if(context != null){
            homeDwellerModel.logoutUser(context, this);
        }

    }

    @Override
    public void setView(HomeDwellerContract.View view) {

        this.homeDwellerView = view;
    }

    @Override
    public void onLogoutSuccess(String logoutResponse) {

        this.homeDwellerView.setSuccessLogout(logoutResponse);

    }

    @Override
    public void onLogoutFail(String logoutResponse) {

        this.homeDwellerView.setFailLogout(logoutResponse);

    }

    @Override
    public void onServerError(String message) {

    }
}
