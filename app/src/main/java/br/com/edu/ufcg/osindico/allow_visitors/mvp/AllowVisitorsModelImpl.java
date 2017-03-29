package br.com.edu.ufcg.osindico.allow_visitors.mvp;

import java.util.List;

import br.com.edu.ufcg.osindico.data.models.VisitorDetails;
import br.com.edu.ufcg.osindico.data.services.DwellerService;

public class AllowVisitorsModelImpl implements AllowVisitorsContract.Model {

    private DwellerService service;

    public AllowVisitorsModelImpl(DwellerService service) {
        this.service = service;
    }

    @Override
    public void registerVisitorsList(String token, List<VisitorDetails> visitorDetails) {

    }
}
