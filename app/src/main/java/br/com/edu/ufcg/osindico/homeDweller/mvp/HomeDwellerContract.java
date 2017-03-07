package br.com.edu.ufcg.osindico.homeDweller.mvp;

import android.content.Context;

import java.util.List;

import br.com.edu.ufcg.osindico.data.models.ServerResponse.MessageResponse;


public interface HomeDwellerContract {

    interface Model {

        interface HomeDwellerListener{
            void onLogoutSuccess(String logoutResponse);
            void onLogoutFail(String logoutResponse);
            void onServerError(String message);
            void onLoadMessagesSuccess(List<MessageResponse> messages);
        }

        void getMessages(String token, HomeDwellerContract.Model.HomeDwellerListener homeDwellerListener);
        void logoutUser(Context context, HomeDwellerContract.Model.HomeDwellerListener homeDwellerListener);
    }

    interface Presenter {
        void loadMessages(String token);
        void logout(Context context);
        void setView(HomeDwellerContract.View view);
        //  void onDestroy();
    }

    interface View {
        void setMessagesList(List<MessageResponse> messages);
        void setSuccessLogout(String logoutResponse);
        void setFailLogout(String logoutResponse);
        void setServerError(String errorMessage);
    }

}
