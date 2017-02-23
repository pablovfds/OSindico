package br.com.edu.ufcg.osindico.registerDweller.mvp;

import br.com.edu.ufcg.osindico.data.models.Contact;

public interface RegisterDwellerContract {

    interface Model {

        interface OnRegisterDwellerListener {
            void onNameError();

            void onEmailError();

            void onPasswordError();

            void onConfirmPasswordError();

            void onPhoneNumberError();

            void onSuccess(String message);

            void onServerError(String serverError);
        }

        void registerDweller(String name, Contact contact, String email, String password, String confirmPassword, Long condominiumId, OnRegisterDwellerListener listener);
    }

    interface Presenter {

        void validateCredentials(String name, Contact contact, String email, String password, String confirmPassword, Long condominiumId);

        void setView(RegisterDwellerContract.View view);

        void onDestroy();
    }

    interface View {

        void setNameError();

        void setPhoneNumberError();

        void setEmailError();

        void setPasswordError();

        void setConfirmPasswordError();

        void setSuccess(String mensagem);

        void setServerError(String serverError);

    }
}
