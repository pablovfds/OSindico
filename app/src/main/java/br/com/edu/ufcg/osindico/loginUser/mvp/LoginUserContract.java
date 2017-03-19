package br.com.edu.ufcg.osindico.loginUser.mvp;

import br.com.edu.ufcg.osindico.data.models.ServerResponse.LoginResponse;

public interface LoginUserContract {

    interface Model {

        interface OnLoginUserListener{
            void onEmailError();
            void onPasswordError();
            void onSuccess(LoginResponse loginResponse);
            void onServerError(String message);
        }

        void loginUser(String email, String senha, OnLoginUserListener listener );
    }

    interface Presenter {
        void validateCredentials(String email, String senha);
        void setView(View view);
        void onDestroy();
    }

    interface View {
        void setEmailError();
        void setPasswordError();
        void setSuccessLogin(LoginResponse loginResponse);
        void setServerError(String errorMessage);

    }
}
