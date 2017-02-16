package br.com.edu.ufcg.osindico.registroDoSindico.mvp;

import br.com.edu.ufcg.osindico.registroDoSindico.model.CondoModel;
import br.com.edu.ufcg.osindico.registroDoSindico.model.SyndicDetails;

public interface RegisterSyndicMVPContract {

    interface RegisterSyndicModel {
        interface OnRegisterSyndicFinishedListener {
            void onUsernameError();

            void onPasswordError();

            void onSuccess();
        }

        interface OnValidateCondoFinishedListener {
            void onUsernameError();

            void onPasswordError();

            void onSuccess();
        }

        interface OnValidateSyndicFinishedListener {
            void onUsernameError();

            void onPasswordError();

            void onSuccess();
        }

        void register(String username, String password, OnRegisterSyndicFinishedListener listener);

        void validateCredentialsSyndic(SyndicDetails syndicModel, OnValidateSyndicFinishedListener listener);

        void validateCredentialsCondo(CondoModel condoModel, OnValidateCondoFinishedListener listener);
    }

    interface RegisterSyndicPresenter {
        void validateCredentials(String username, String password);

        void onDestroy();
    }

    interface RegisterSyndicView {
        void showProgress();

        void hideProgress();

        void setUsernameError();

        void setPasswordError();

        void navigateToHome();
    }

}
