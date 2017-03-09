package br.com.edu.ufcg.osindico.homeDweller.mvp;

import android.content.Context;

/**
 * Created by Lucio on 22/02/2017.
 */

public interface HomeDwellerContract {

    interface Model {

        interface HomeDwellerListener{
            void onLogoutSuccess(String logoutResponse);
            void onLogoutFail(String logoutResponse);
            void onServerError(String message);
        }
        void logoutUser(Context context, HomeDwellerContract.Model.HomeDwellerListener homeDwellerListener);
    }

    interface Presenter {
        void logout(Context context);
        void setView(HomeDwellerContract.View view);
        //  void onDestroy();
    }

    interface View {
        // void setServerError(String errorMessage);
        void setSuccessLogout(String logoutResponse);
        void setFailLogout(String logoutResponse);
    }

}
