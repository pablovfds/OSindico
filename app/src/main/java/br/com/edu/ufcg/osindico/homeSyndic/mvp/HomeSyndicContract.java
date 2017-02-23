package br.com.edu.ufcg.osindico.homeSyndic.mvp;

import android.content.Context;

import br.com.edu.ufcg.osindico.data.models.ServerResponse.LoginResponse;
import br.com.edu.ufcg.osindico.loginUser.mvp.LoginUserContract;

/**
 * Created by Lucio on 22/02/2017.
 */

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
      //  void onDestroy();
    }

    interface View {
       // void setServerError(String errorMessage);
        void setSuccessLogout(String logoutResponse);
        void setFailLogout(String logoutResponse);
    }
}
