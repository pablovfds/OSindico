package br.com.edu.ufcg.osindico.registerSyndic.mvp;

import br.com.edu.ufcg.osindico.data.models.SyndicDetails;

public interface RegisterSyndicContract {

    interface Model {

        interface OnValidateSyndicListener {
            void onNameError();

            void onEmailError();

            void onPhoneError();

            void onPasswordError();

            void onConfirmPasswordError();

            void onSuccess();
        }

        void validateCredentialsSyndic(SyndicDetails syndicModel, OnValidateSyndicListener listener);
    }

    interface Presenter {
        void validateCredentials(String username, String password);

        void setView(View view);

        void onDestroy();
    }

    interface View {
        void showProgress();

        void hideProgress();

        void setUsernameError();

        void setPasswordError();

        void navigateToRegisterCondo();
    }

}
