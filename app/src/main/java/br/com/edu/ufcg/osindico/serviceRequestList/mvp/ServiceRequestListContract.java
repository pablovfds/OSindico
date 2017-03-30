package br.com.edu.ufcg.osindico.serviceRequestList.mvp;

import java.util.List;

import br.com.edu.ufcg.osindico.base.BasePresenter;
import br.com.edu.ufcg.osindico.base.BaseView;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.ServiceRequestResponse;

public interface ServiceRequestListContract {

    interface Model {
        interface OnServiceRequestListListener {

            void onTokenError();

            void onServerError(String message);

            void onLoadListSuccess(List<ServiceRequestResponse> servicesList);
            void onUpdateStatusSuccess(String message);
        }
        void loadServicesList(String token, OnServiceRequestListListener listener);

        void updateServiceRequestStatus(String token,Long id, OnServiceRequestListListener listener);
    }

    interface View extends BaseView {
        void setRequestList(List<ServiceRequestResponse> requestList);
        void setTokenError();
        void setServerError(String message);
        void setUpdateStatusSuccess(String message);
    }

    interface Presenter extends BasePresenter {
        void loadRequestList(String token);
        void updateServiceRequestStatus(String token,Long id);
    }
}
