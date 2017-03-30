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
    public void sendVisitorsList(String token, String date, List<VisitorDetails> visitors) {
        model.registerVisitorsList(token, date, visitors, this);
    }

    @Override
    public void checkVisitor(String name, String cpf) {

        if (name.isEmpty()) {
            view.setNameError();
        }

        if (cpf.isEmpty()) {
            view.setCpfError();
        }

        view.onFinishAddDialog(name, cpf);

    }

    @Override
    public void setView(AllowVisitorsContract.View view) {
        this.view = view;
    }

    @Override
    public void onSuccess() {
        view.setSuccess();
    }

    @Override
    public void onServerError(String message) {
        view.setServerError(message);
    }
}
