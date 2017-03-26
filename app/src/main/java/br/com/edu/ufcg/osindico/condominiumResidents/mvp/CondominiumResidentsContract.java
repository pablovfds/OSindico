package br.com.edu.ufcg.osindico.condominiumResidents.mvp;

import java.util.List;

import br.com.edu.ufcg.osindico.base.BaseView;
import br.com.edu.ufcg.osindico.condominium_rules.mvp.CondominiumRulesContract;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.DwellerResponse;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.UserResponse;


public interface CondominiumResidentsContract {

    interface View {
        void showProgress();

        void hideProgress();

        void setServerError(String errorMessage);

        void setCondominiumResidentsList(List<DwellerResponse> residentsResponseList);
    }

    interface Presenter {
        void loadCondominiumResidents(String token);

        void setView(CondominiumResidentsContract.View view);

        void onDestroy();
    }

    interface Model {
        interface OnCondominiumResidentsListener {

            void onSuccess(List<DwellerResponse> residentsResponseList);

            void onServerError(String message);
        }

        void getCondominiumResidents(String token, OnCondominiumResidentsListener listener);
    }
}
