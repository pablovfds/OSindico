package br.com.edu.ufcg.osindico.request_service.mvp;

import br.com.edu.ufcg.osindico.base.BaseListener;
import br.com.edu.ufcg.osindico.base.BasePresenter;
import br.com.edu.ufcg.osindico.base.BaseView;
import br.com.edu.ufcg.osindico.data.models.ServiceRequest;

public interface RequestServiceContract {
    interface Model {

        void sendRequest(String token, ServiceRequest request, BaseListener listener);

    }

    interface View extends BaseView {
        void setSuccess();
        void setServerError(String message);
        void showTokenError();
        void showTitleError();
        void showDescriptionError();
    }

    interface Presenter extends BasePresenter {
        void validateService(String token, String title, String description);

        void setModel(Model model);

        View getView();
    }
}
