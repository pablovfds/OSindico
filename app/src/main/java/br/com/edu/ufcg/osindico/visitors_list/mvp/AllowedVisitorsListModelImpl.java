package br.com.edu.ufcg.osindico.visitors_list.mvp;

import br.com.edu.ufcg.osindico.data.services.SyndicService;

public class AllowedVisitorsListModelImpl implements AllowedVisitorsListContract.Model {

    private SyndicService dwellerService;

    public AllowedVisitorsListModelImpl(SyndicService service) {
        this.dwellerService = service;
    }

    @Override
    public void getAllowedVisitorsList(String token, OnAllowedVisitorsListListener listener) {

    }
}
