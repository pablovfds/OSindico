package br.com.edu.ufcg.osindico.dwellerServiceList.mvp;

import java.util.List;

import br.com.edu.ufcg.osindico.base.BaseView;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.ServiceRequestResponse;
import br.com.edu.ufcg.osindico.data.services.DwellerService;

public class DwellerServiceListPresenterImpl implements DwellerServiceListContract.Presenter,
        DwellerServiceListContract.Model.OnDwellerServiceListListener{

    private DwellerServiceListContract.Model model;
    private DwellerServiceListContract.View view;

    public DwellerServiceListPresenterImpl(DwellerService service) {
        this.model = new DwellerServiceListModelImpl(service);
    }

    @Override
    public void onSuccess(List<ServiceRequestResponse> responseList) {
        if (view != null) {
            view.hideProgress();
            view.setServiceList(responseList);
        }
    }

    @Override
    public void onServerError(String errorMessage) {
        if (view != null) {
            view.hideProgress();
            view.setServerError(errorMessage);
        }
    }

    @Override
    public void loadDwellerServiceList(String token) {
        if (view != null) {
            view.showProgress();
            model.getDwellerServiceList(token, this);
        }
    }

    @Override
    public void setView(BaseView view) {
        this.view = (DwellerServiceListContract.View) view;
    }

    @Override
    public void onDestroy() {
        view = null;
    }
}
