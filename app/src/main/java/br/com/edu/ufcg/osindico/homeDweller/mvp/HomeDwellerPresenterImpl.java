package br.com.edu.ufcg.osindico.homeDweller.mvp;


import android.content.Context;
import android.util.Log;

import java.util.List;

import br.com.edu.ufcg.osindico.data.models.ServerResponse.MessageResponse;
import br.com.edu.ufcg.osindico.data.services.DwellerService;
import br.com.edu.ufcg.osindico.data.services.SyndicService;
import br.com.edu.ufcg.osindico.homeSyndic.mvp.HomeSyndicModelImpl;

public class HomeDwellerPresenterImpl implements HomeDwellerContract.Presenter, HomeDwellerContract.Model.HomeDwellerListener {

    private HomeDwellerContract.View homeDwellerView;
    private HomeDwellerContract.Model homeDwellerModel;

    public HomeDwellerPresenterImpl(DwellerService service){
        this.homeDwellerModel = new HomeDwellerModelImpl(service);
    }

    @Override
    public void loadMessages(String token) {
        homeDwellerModel.getMessages(token, this);
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

    @Override
    public void onLoadMessagesSuccess(List<MessageResponse> messages) {
       // Log.e("messages size", messages.size() + "");
        this.homeDwellerView.setMessagesList(messages);
    }
}
