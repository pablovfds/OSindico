package br.com.edu.ufcg.osindico.syndicMessages.mvp;

public interface SyndicMessageContract {

    interface Model {
        interface OnSendMessageListener {
            void onSuccess();

            void onServerError();
        }

        void sendMessage();
    }

    interface Presenter {
        void validateMessage();

        void setView();

        void onDestroy();
    }

    interface View {
        void showProgress();

        void hideProgress();

        void setServerFailed();

        void navigateToHomeSyndic();
    }
}
