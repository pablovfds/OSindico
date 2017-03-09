package br.com.edu.ufcg.osindico.homeSyndic.mvp;

import br.com.edu.ufcg.osindico.data.services.SyndicService;

public class HomeSyndicModelImpl implements HomeSyndicContract.Model {

    private SyndicService syndicService;

    public HomeSyndicModelImpl(SyndicService syndicService) {
        this.syndicService = syndicService;
    }

}
