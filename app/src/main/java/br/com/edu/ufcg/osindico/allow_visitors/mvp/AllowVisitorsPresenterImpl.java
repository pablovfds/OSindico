package br.com.edu.ufcg.osindico.allow_visitors.mvp;

import br.com.edu.ufcg.osindico.data.services.SyndicService;

public class AllowVisitorsPresenterImpl implements AllowVisitorsContract.Presenter {

    private AllowVisitorsContract.View view;
    private AllowVisitorsContract.Model model;

    public AllowVisitorsPresenterImpl(SyndicService service, AllowVisitorsContract.View view) {
        model = new AllowVisitorsModelImpl(service);
        this.view = view;
    }

    @Override
    public void validateData(String token, String name, String cpf) {

    }
}
