package br.com.edu.ufcg.osindico.homeDweller.mvp;


import java.util.List;

import br.com.edu.ufcg.osindico.data.models.ServerResponse.MessageResponse;
import br.com.edu.ufcg.osindico.data.services.DwellerService;

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
    public void setView(HomeDwellerContract.View view) {
        this.homeDwellerView = view;
    }

    @Override
    public void onServerError(String message) {

    }

    @Override
    public void onLoadMessagesSuccess(List<MessageResponse> messages) {
        this.homeDwellerView.setMessagesList(messages);
    }
}
