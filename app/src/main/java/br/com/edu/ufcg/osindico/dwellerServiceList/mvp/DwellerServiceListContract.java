package br.com.edu.ufcg.osindico.dwellerServiceList.mvp;

import java.util.List;

import br.com.edu.ufcg.osindico.base.BasePresenter;
import br.com.edu.ufcg.osindico.base.BaseView;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.ServiceRequestResponse;

public interface DwellerServiceListContract {

    interface Model {

        interface OnDwellerServiceListListener {
            void onSuccess(List<ServiceRequestResponse> responseList);
            void onServerError(String errorMessage);
        }

        void getDwellerServiceList(String token, OnDwellerServiceListListener listener);
    }

    interface View extends BaseView {
        void setServerError(String message);
        void setServiceList(List<ServiceRequestResponse> responseList);
    }

    interface Presenter extends BasePresenter{
        void loadDwellerServiceList(String token);
    }
}
