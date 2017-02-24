package br.com.edu.ufcg.osindico.residentDetails.mvp;

public interface ResidentDetailsContract {
    interface Model {

        interface OnResidentDetailsListener {

            void onSuccess();

            void onServerError(String message);
        }

        void sendResponseRequest(String token, Long id, boolean status, OnResidentDetailsListener listener);
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

        void navigateToRequestsResidents();
    }
}
