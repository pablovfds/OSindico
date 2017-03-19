package br.com.edu.ufcg.osindico.serviceRequestList.mvp;

import br.com.edu.ufcg.osindico.data.services.SyndicService;

public class ServiceRequestListModelImpl implements ServiceRequestListContract.Model {

    private SyndicService syndicService;

    public ServiceRequestListModelImpl(SyndicService syndicService) {
        this.syndicService = syndicService;
    }

    @Override
    public void loadServicesList(String token, OnServiceRequestListListener listener) {
        if(token == null || token.isEmpty()){
            listener.onTokenError();
        } else {
            //// TODO: 19/03/2017 implementação da lista 
        }
    }
}
