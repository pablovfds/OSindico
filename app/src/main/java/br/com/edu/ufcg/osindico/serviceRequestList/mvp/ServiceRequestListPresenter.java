package br.com.edu.ufcg.osindico.serviceRequestList.mvp;

import java.util.List;

import br.com.edu.ufcg.osindico.base.BaseView;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.ServiceRequestResponse;
import br.com.edu.ufcg.osindico.data.services.SyndicService;

public class ServiceRequestListPresenter implements ServiceRequestListContract.Presenter,
        ServiceRequestListContract.Model.OnServiceRequestListListener {

    private ServiceRequestListContract.View view;
    private ServiceRequestListContract.Model model;

    public ServiceRequestListPresenter(SyndicService service) {
        this.model = new ServiceRequestListModelImpl(service);
    }

    @Override
    public void setView(BaseView view) {
        this.view = (ServiceRequestListContract.View) view;
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }

    @Override
    public void loadRequestList(String token) {
        if (view != null) {
            this.view.showProgress();
            this.model.loadServicesList(token, this);
        }
    }

    @Override
    public void updateServiceRequestStatus(String token, Long id) {
        if (view != null) {
            this.model.updateServiceRequestStatus(token, id, this);
        }
    }

    @Override
    public void onTokenError() {
        if (view != null) {
            this.view.hideProgress();
            this.view.setTokenError();
        }
    }

    @Override
    public void onServerError(String message) {
        this.view.setServerError(message);
    }

    @Override
    public void onLoadListSuccess(List<ServiceRequestResponse> servicesList) {
        if (view != null) {
            this.view.hideProgress();
            this.view.setRequestList(servicesList);
        }
    }

    @Override
    public void onUpdateStatusSuccess(String message) {
        if (view != null) {
            this.view.hideProgress();
            this.view.setUpdateStatusSuccess(message);
        }
    }
}
