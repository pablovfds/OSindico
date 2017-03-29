package br.com.edu.ufcg.osindico.visitors_list.mvp;

import java.util.List;

import br.com.edu.ufcg.osindico.base.BaseView;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.VisitorResponse;
import br.com.edu.ufcg.osindico.data.services.SyndicService;

public class AllowedVisitorsListPresenterImpl implements AllowedVisitorsListContract.Presenter,
        AllowedVisitorsListContract.Model.OnAllowedVisitorsListListener {

    private AllowedVisitorsListContract.Model model;
    private AllowedVisitorsListContract.View view;

    public AllowedVisitorsListPresenterImpl(SyndicService service) {
        this.model = new AllowedVisitorsListModelImpl(service);
    }

    @Override
    public void setView(BaseView view) {
        this.view = (AllowedVisitorsListContract.View) view;
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }

    @Override
    public void onSuccess(List<VisitorResponse> visitorDetailsList) {
        if (this.view != null) {
            this.view.hideProgress();
            this.view.setAllowedVisitorsList(visitorDetailsList);
        }
    }

    @Override
    public void onServerError(String message) {
        if (this.view != null) {
            this.view.hideProgress();
            this.view.setServerError(message);
        }
    }

    @Override
    public void loadAllowedVisitorsList(String token) {
        if (this.view != null) {
            this.view.hideProgress();
            this.model.getAllowedVisitorsList(token, this);
        }
    }
}
