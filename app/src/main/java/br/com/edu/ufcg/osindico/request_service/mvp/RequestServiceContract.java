package br.com.edu.ufcg.osindico.request_service.mvp;

import br.com.edu.ufcg.osindico.base.BaseListener;
import br.com.edu.ufcg.osindico.base.BasePresenter;
import br.com.edu.ufcg.osindico.base.BaseView;
import br.com.edu.ufcg.osindico.data.models.ServiceRequest;

public interface RequestServiceContract {
    interface Model {
        interface OnRequestServiceListener extends BaseListener {
            void onTokenError();
            void onTypeError();
            void onDescriptionError();
            void onTitleError();
        }

        void sendRequest(String token, ServiceRequest request, OnRequestServiceListener listener);

        boolean validateData(String token,String title, String type, String description,
                             OnRequestServiceListener listener);
    }

    interface View extends BaseView {

    }

    interface Presenter extends BasePresenter {

    }
}
