package br.com.edu.ufcg.osindico.requests_residents.mvp;


import br.com.edu.ufcg.osindico.data.services.SyndicService;

public class RequestsResidentsModelImpl implements RequestsResidentsContract.Model {
    private SyndicService syndicService;

    public RequestsResidentsModelImpl(SyndicService syndicService) {
        this.syndicService = syndicService;
    }

    @Override
    public void getRequestsResidents(String token, OnRequestsResidentsListener listener) {
    }
}
