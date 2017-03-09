package br.com.edu.ufcg.osindico.registerRegraSyndic.mvp;

public interface RegisterRuleContract {

    interface Model {

        interface OnRegisterRegraSyndicListener {

            void onRuleError();

            void onServerError(String message);

            void onSuccess();

        }

        void registerRuleSyndic(String regra, String token, RegisterRuleContract.Model.OnRegisterRegraSyndicListener listener);
    }

    interface Presenter {

        void validateRule(String regra, String token);

        void setView(RegisterRuleContract.View view);

        void onDestroy();
    }

    interface View {

        void navigateToRulesCondoList();

        void setServerError(String serverError);

        void setRuleError();


    }
}
