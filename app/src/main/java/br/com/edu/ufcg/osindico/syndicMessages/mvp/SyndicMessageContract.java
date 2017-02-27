package br.com.edu.ufcg.osindico.syndicMessages.mvp;

import br.com.edu.ufcg.osindico.data.services.SyndicService;

public interface SyndicMessageContract {

    interface Model {
        interface OnSendMessageListener {

            void onMessageError();

            void onSuccess();

            void onServerError(String errorMessage);
        }

        void sendMessage(String message, SyndicService service, OnSendMessageListener listener);
    }

    interface Presenter {
        void validateMessage(String message, SyndicService service);

        void setView(View view);

        void onDestroy();
    }

    interface View {
        void showProgress();

        void hideProgress();

        void setMessageError();

        void setServerFailed(String errorMessage);

        void navigateToHomeSyndic();
    }
}
