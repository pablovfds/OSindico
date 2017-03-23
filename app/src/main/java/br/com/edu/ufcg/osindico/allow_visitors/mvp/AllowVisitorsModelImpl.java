package br.com.edu.ufcg.osindico.allow_visitors.mvp;

import java.util.List;

import br.com.edu.ufcg.osindico.data.models.VisitorDetails;
import br.com.edu.ufcg.osindico.data.services.SyndicService;

public class AllowVisitorsModelImpl implements AllowVisitorsContract.Model {

    private SyndicService service;

    public AllowVisitorsModelImpl(SyndicService service) {
        this.service = service;
    }

    @Override
    public void sendVisitorsList(String token, List<VisitorDetails> visitorDetails) {

    }
}
