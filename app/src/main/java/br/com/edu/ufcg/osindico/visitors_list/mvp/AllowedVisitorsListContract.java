package br.com.edu.ufcg.osindico.visitors_list.mvp;

import java.util.List;

import br.com.edu.ufcg.osindico.base.BasePresenter;
import br.com.edu.ufcg.osindico.base.BaseView;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.VisitorResponse;

public interface AllowedVisitorsListContract {
    interface Model {
        interface OnAllowedVisitorsListListener {
            void onSuccess(List<VisitorResponse> visitorDetailsList);
            void onServerError(String message);
        }

        void getAllowedVisitorsList(String token, OnAllowedVisitorsListListener listener);
    }

    interface View extends BaseView {
        void setAllowedVisitorsList(List<VisitorResponse> visitorsList);
        void setServerError(String message);
    }

    interface Presenter extends BasePresenter{
        void loadAllowedVisitorsList(String token);
    }
}
