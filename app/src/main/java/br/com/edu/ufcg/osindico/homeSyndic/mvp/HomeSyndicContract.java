package br.com.edu.ufcg.osindico.homeSyndic.mvp;

import android.content.Context;


public interface HomeSyndicContract {

    interface Model {

        interface HomeSyndicListener{
            void onLogoutSuccess(String logoutResponse);
            void onLogoutFail(String logoutResponse);
            void onServerError(String message);
        }
        void logoutUser(Context context, HomeSyndicListener homeSyndicListener);
    }

    interface Presenter {
        void logout(Context context);
        void setView(HomeSyndicContract.View view);
        void onDestroy();
    }

    interface View {
        void setSuccessLogout(String logoutResponse);
        void setFailLogout(String logoutResponse);
        void setServerError(String errorMessage);

    }
}
