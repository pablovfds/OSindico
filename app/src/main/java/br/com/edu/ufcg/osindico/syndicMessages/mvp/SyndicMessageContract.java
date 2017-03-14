package br.com.edu.ufcg.osindico.syndicMessages.mvp;

import br.com.edu.ufcg.osindico.data.services.SyndicService;

public interface SyndicMessageContract {

    interface Model {
        interface OnSendMessageListener {

            void onMessageLengthError();

            void onMessageNullError();

            void onSuccess();

            void onServerError(String errorMessage);
        }

        void sendMessage(String message, String token ,SyndicService service,
                         OnSendMessageListener listener);
    }

    interface Presenter {
        void validateMessage(String message, String token ,SyndicService service);

        void setView(View view);

        void onDestroy();
    }

    interface View {
        void showProgress();

        void hideProgress();

        void setMessageLengthError();

        void setMessageNullError();

        void setServerFailed(String errorMessage);

        void navigateToHomeSyndic();
    }
}
