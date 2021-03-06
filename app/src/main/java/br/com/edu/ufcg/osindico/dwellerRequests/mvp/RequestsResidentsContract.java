package br.com.edu.ufcg.osindico.dwellerRequests.mvp;


import java.util.List;

import br.com.edu.ufcg.osindico.data.models.ServerResponse.DwellerResponse;

public interface RequestsResidentsContract {
    interface Model {

        interface OnRequestsResidentsListener {

            void onSuccess(List<DwellerResponse> residents);

            void onServerError(String message);
        }

        void getRequestsResidents(String token, OnRequestsResidentsListener listener);
    }

    interface Presenter {
        void loadRequestsResidents(String token);

        void setView(View view);

        void onDestroy();
    }

    interface View {
        void showProgress();

        void hideProgress();

        void setServerError(String errorMessage);

        void setRequestsResidentsList(List<DwellerResponse> residents);
    }

}
