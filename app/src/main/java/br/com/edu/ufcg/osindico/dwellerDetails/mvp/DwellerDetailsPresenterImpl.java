package br.com.edu.ufcg.osindico.dwellerDetails.mvp;

import br.com.edu.ufcg.osindico.data.services.SyndicService;

public class DwellerDetailsPresenterImpl implements DwellerDetailsContract.Presenter,
        DwellerDetailsContract.Model.OnDwellerDetailsListener {

    private DwellerDetailsContract.Model model;
    private DwellerDetailsContract.View view;

    public DwellerDetailsPresenterImpl(SyndicService service) {
        this.model = new DwellerDetailsModelImpl(service);
    }

    @Override
    public void sendResponseRequest(String token, Long id, boolean status) {
        if (view != null){
            this.view.showProgress();
            this.model.sendResponseRequest(token, id, status, this);
        }
    }

    @Override
    public void setView(DwellerDetailsContract.View newView) {
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
            this.view.navigateToRequestsDweller();
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
