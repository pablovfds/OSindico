package br.com.edu.ufcg.osindico.request_service.mvp;

import br.com.edu.ufcg.osindico.base.BaseListener;
import br.com.edu.ufcg.osindico.base.BaseView;
import br.com.edu.ufcg.osindico.data.models.ServiceRequest;
import br.com.edu.ufcg.osindico.data.services.DwellerService;

public class RequestServicePresenterImpl implements RequestServiceContract.Presenter, BaseListener {

    private RequestServiceContract.View view;
    private RequestServiceContract.Model model;

    public RequestServicePresenterImpl(DwellerService service, RequestServiceContract.View view) {
        model = new RequestServiceModelImpl(service);
        this.view = view;
    }

    public void setModel(RequestServiceContract.Model model) {
        this.model = model;
    }

    @Override
    public void validateService(String token, String title, String description) {
        boolean error = false;
        if (token.isEmpty()) {
            onTokenError();
            error = true;
        }
        if (title.isEmpty()) {
            onTitleError();
            error = true;
        }
        if (description.isEmpty()) {
            onDescriptionError();
            error = true;
        }

        if (!error) {
            model.sendRequest(token, new ServiceRequest(title, description), this);
        }
    }

    @Override
    public void setView(BaseView view) {

    }

    @Override
    public void onDestroy() {

    }

    private void onTokenError() {
        if (view != null) {
            view.showTokenError();
        }
    }

    private void onTitleError() {
        if (view != null) {
            view.showTitleError();
        }
    }

    private void onDescriptionError() {
        if (view != null) {
            view.showDescriptionError();
        }
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onServerError(String message) {

    }
}
