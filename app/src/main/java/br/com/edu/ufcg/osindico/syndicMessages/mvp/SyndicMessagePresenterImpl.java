package br.com.edu.ufcg.osindico.syndicMessages.mvp;

import br.com.edu.ufcg.osindico.data.services.SyndicService;

public class SyndicMessagePresenterImpl implements SyndicMessageContract.Presenter,
        SyndicMessageContract.Model.OnSendMessageListener {

    private SyndicMessageContract.Model model;
    private SyndicMessageContract.View view;

    public SyndicMessagePresenterImpl() {
        this.model = new SyndicMessageModelImpl();
    }

    @Override
    public void validateMessage(String message,String token, SyndicService service) {
        if (view != null){
            this.view.showProgress();
            this.model.sendMessage(message,token, service, this);
        }
    }

    @Override
    public void setView(SyndicMessageContract.View newView) {
        if (newView != null){
            this.view = newView;
        }
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }

    @Override
    public void onMessageLengthError() {
        if (view != null){
            this.view.hideProgress();
            this.view.setMessageLengthError();
        }
    }

    @Override
    public void onMessageNullError() {
        if (view != null){
            this.view.hideProgress();
            this.view.setMessageNullError();
        }
    }

    @Override
    public void onSuccess() {
        if (view != null){
            this.view.hideProgress();
            this.view.navigateToHomeSyndic();
        }
    }

    @Override
    public void onServerError(String errorMessage) {
        if (view != null){
            this.view.hideProgress();
            this.view.setServerFailed(errorMessage);
        }
    }
}
