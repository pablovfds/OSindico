package br.com.edu.ufcg.osindico.dwellerDetails.mvp;

public interface DwellerDetailsContract {
    interface Model {

        interface OnDwellerDetailsListener {

            void onSuccess();

            void onServerError(String message);
        }

        void sendResponseRequest(String token, Long id, boolean status, OnDwellerDetailsListener listener);
    }

    interface Presenter {
        void sendResponseRequest(String token, Long id, boolean status);

        void setView(View view);

        void onDestroy();
    }

    interface View {
        void showProgress();

        void hideProgress();

        void setServerError(String errorMessage);

        void navigateToRequestsDweller();
    }
}
