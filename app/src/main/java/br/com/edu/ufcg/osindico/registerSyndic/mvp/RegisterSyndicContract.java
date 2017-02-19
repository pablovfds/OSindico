package br.com.edu.ufcg.osindico.registerSyndic.mvp;

import br.com.edu.ufcg.osindico.data.models.SyndicDetails;

public interface RegisterSyndicContract {

    interface Model {

        interface OnRegisterSyndicListener {
            void onNameError();

            void onEmailError();

            void onPhoneError();

            void onPasswordError();

            void onConfirmPasswordError();

            void onSuccess(Long syndicId);

            void onServerError(String message);
        }

        void registerSyndic(String name, String email, String password, String confirmPassword,
                            String phone, OnRegisterSyndicListener listener);
    }

    interface Presenter {
        void validateCredentials(String name,String email, String password,
                                 String confirmPassword, String phone);

        void setView(View view);

        void onDestroy();
    }

    interface View {
        void showProgress();

        void hideProgress();

        void setNameError();

        void setEmailError();

        void setPasswordError();

        void setConfirmPasswordError();

        void setPhoneError();

        void setServerError(String errorMessage);

        void navigateToRegisterCondo(Long syndicId);
    }

}
