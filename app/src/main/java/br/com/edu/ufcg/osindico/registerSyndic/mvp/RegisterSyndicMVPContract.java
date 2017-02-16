package br.com.edu.ufcg.osindico.registerSyndic.mvp;

import br.com.edu.ufcg.osindico.data.models.SyndicDetails;

public interface RegisterSyndicMVPContract {

    interface RegisterSyndicModel {

        interface OnValidateSyndicFinishedListener {
            void onNameError();

            void onEmailError();

            void onPhoneError();

            void onPasswordError();

            void onConfirmPasswordError();

            void onSuccess();
        }

        void validateCredentialsSyndic(SyndicDetails syndicModel, OnValidateSyndicFinishedListener listener);
    }

    interface RegisterSyndicPresenter {
        void validateCredentials(String username, String password);

        void setView(RegisterSyndicView view);

        void onDestroy();
    }

    interface RegisterSyndicView {
        void showProgress();

        void hideProgress();

        void setUsernameError();

        void setPasswordError();

        void navigateToRegisterCondo();
    }

}
