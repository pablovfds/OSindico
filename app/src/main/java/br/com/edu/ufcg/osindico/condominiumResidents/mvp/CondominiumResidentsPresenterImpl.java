package br.com.edu.ufcg.osindico.condominiumResidents.mvp;

import java.util.List;

import br.com.edu.ufcg.osindico.data.models.ServerResponse.DwellerResponse;
import br.com.edu.ufcg.osindico.data.services.SyndicService;


public class CondominiumResidentsPresenterImpl implements CondominiumResidentsContract.Presenter, CondominiumResidentsContract.Model.OnCondominiumResidentsListener {

    private CondominiumResidentsContract.View view;
    private CondominiumResidentsContract.Model model;

    public CondominiumResidentsPresenterImpl(SyndicService syndicService) {
        this.model = new CondominiumResidentsModelImpl(syndicService);
    }


    @Override
    public void loadCondominiumResidents(String token) {
        if(view != null){
            this.view.showProgress();
            this.model.getCondominiumResidents(token, this);
        }
    }

    @Override
    public void setView(CondominiumResidentsContract.View view) {
        this.view = view;

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onSuccess(List<DwellerResponse> residentsResponseList) {
        if(view != null){
            this.view.hideProgress();
            this.view.setCondominiumResidentsList(residentsResponseList);
        }
    }

    @Override
    public void onServerError(String message) {

    }
}
