package br.com.edu.ufcg.osindico.allow_visitors.mvp;

import java.util.List;

import br.com.edu.ufcg.osindico.data.models.VisitorDetails;
import br.com.edu.ufcg.osindico.data.services.DwellerService;

public class AllowVisitorsPresenterImpl implements AllowVisitorsContract.Presenter {

    private AllowVisitorsContract.View view;
    private AllowVisitorsContract.Model model;

    public AllowVisitorsPresenterImpl(DwellerService service) {
        model = new AllowVisitorsModelImpl(service);
    }

    @Override
    public void sendVisitorsList(List<VisitorDetails> visitors) {

    }

    @Override
    public void checkVisitor(String name, String cpf) {

    }

    public void setView(AllowVisitorsContract.View view) {
        this.view = view;
    }
}
