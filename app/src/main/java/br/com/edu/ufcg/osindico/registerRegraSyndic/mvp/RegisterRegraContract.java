package br.com.edu.ufcg.osindico.registerRegraSyndic.mvp;

public interface RegisterRegraContract {

    interface Model {

        interface OnRegisterRegraSyndicListener {

            void onRegraError();

            void onServerError(String message);

            void onSuccess();

        }

        void registerRegraSyndic(String regra, String token, RegisterRegraContract.Model.OnRegisterRegraSyndicListener listener);
    }

    interface Presenter {

        void validateRegra(String regra, String token);

        void setView(RegisterRegraContract.View view);

        void onDestroy();
    }

    interface View {
        void showProgress();

        void hideProgress();

        void setServerError(String errorMessage);

        void navigateToRulesCondoList();


    }
}
