package br.com.edu.ufcg.osindico.serviceRequestList.mvp;

import java.util.List;

import br.com.edu.ufcg.osindico.base.BasePresenter;
import br.com.edu.ufcg.osindico.base.BaseView;

public interface ServiceRequestListContract {

    interface Model {
        interface OnServiceRequestListListener {
            void onServerError(String message);

            void onSuccess(List<Object> servicesList);
        }
        void loadServicesList(String token, OnServiceRequestListListener listener);
    }

    interface View extends BaseView {

    }

    interface Presenter extends BasePresenter {

    }
}
