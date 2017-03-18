package br.com.edu.ufcg.osindico.registerSyndic.mvp;

import br.com.edu.ufcg.osindico.base.BasePresenter;
import br.com.edu.ufcg.osindico.base.BaseView;

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

    interface Presenter extends BasePresenter {
        void validateCredentials(String name,String email, String password,
                                 String confirmPassword, String phone);
    }

    interface View extends BaseView {

        void setNameError();

        void setEmailError();

        void setPasswordError();

        void setConfirmPasswordError();

        void setPhoneError();

        void setServerError(String errorMessage);

        void navigateToRegisterCondo(Long syndicId);
    }

}
