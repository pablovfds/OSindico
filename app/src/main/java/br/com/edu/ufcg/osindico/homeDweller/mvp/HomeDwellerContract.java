package br.com.edu.ufcg.osindico.homeDweller.mvp;

import java.util.List;

import br.com.edu.ufcg.osindico.data.models.ServerResponse.MessageResponse;


public interface HomeDwellerContract {

    interface Model {

        interface HomeDwellerListener{
            void onServerError(String message);
            void onLoadMessagesSuccess(List<MessageResponse> messages);
        }

        void getMessages(String token, HomeDwellerContract.Model.HomeDwellerListener homeDwellerListener);
    }

    interface Presenter {
        void loadMessages(String token);
        void setView(HomeDwellerContract.View view);
        //  void onDestroy();
    }

    interface View {
        void setMessagesList(List<MessageResponse> messages);
        void setServerError(String errorMessage);
    }

}
