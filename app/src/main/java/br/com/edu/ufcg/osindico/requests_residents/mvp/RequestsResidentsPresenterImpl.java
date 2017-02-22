package br.com.edu.ufcg.osindico.requests_residents.mvp;


import java.util.List;

import br.com.edu.ufcg.osindico.data.services.SyndicService;

public class RequestsResidentsPresenterImpl implements RequestsResidentsContract.Presenter,
        RequestsResidentsContract.Model.OnRequestsResidentsListener {

    private RequestsResidentsContract.View view;
    private RequestsResidentsContract.Model model;

    public RequestsResidentsPresenterImpl(SyndicService service) {
        this.model = new RequestsResidentsModelImpl(service);
    }

    @Override
    public void loadRequestsResidents(String token) {
        if (view != null){
            this.view.showProgress();
            this.model.getRequestsResidents(token, this);
        }
    }

    @Override
    public void setView(RequestsResidentsContract.View newView) {
        if (newView != null){
            this.view = newView;
        }
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }

    @Override
    public void onSuccess(List<String> residents) {
        if (view != null){
            this.view.hideProgress();
            this.view.setRequestsResidentsList(residents);
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
