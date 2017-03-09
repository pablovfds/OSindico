package br.com.edu.ufcg.osindico.residentDetails.mvp;

import br.com.edu.ufcg.osindico.data.services.SyndicService;

public class ResidentDetailsPresenterImpl implements ResidentDetailsContract.Presenter,
        ResidentDetailsContract.Model.OnResidentDetailsListener{

    private ResidentDetailsContract.Model model;
    private ResidentDetailsContract.View view;

    public ResidentDetailsPresenterImpl(SyndicService service) {
        this.model = new ResidentDetailsModelImpl(service);
    }

    @Override
    public void sendResponseRequest(String token, Long id, boolean status) {
        if (view != null){
            this.view.showProgress();
            this.model.sendResponseRequest(token, id, status, this);
        }
    }

    @Override
    public void setView(ResidentDetailsContract.View newView) {
        if (newView != null){
            this.view = newView;
        }
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }

    @Override
    public void onSuccess() {
        if (view != null){
            this.view.hideProgress();
            this.view.navigateToRequestsResidents();
        }
    }

    @Override
    public void onServerError(String message) {
        if (view != null){
            this.view.hideProgress();
            this.view.setServerError(message);
        }
    }
}
