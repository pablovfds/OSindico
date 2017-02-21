package br.com.edu.ufcg.osindico.loginUser.mvp;

public interface LoginUserContract {
    interface Model {
        interface OnLoginUserListener{
            void onEmailError();
            void onPasswordError();
            void onSuccess();
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
        void showProgress();
        void hideProgress();
        void setEmailError();
        void setPasswordError();
        void setServerError(String errorMessage);
    }
}
