package br.com.edu.ufcg.osindico.condominium_rules.mvp;

import java.util.List;

import br.com.edu.ufcg.osindico.data.models.ServerResponse.RuleResponse;

/**
 * Created by emanoel on 02/03/17.
 */

public interface CondominiumRulesContract {

    interface View {
        void showProgress();

        void initializeViews();

        void hideProgress();

        void setServerError(String errorMessage);

        void setCondominiumRulesList(List<RuleResponse> ruleResponseList);
    }

    interface Presenter {
        void loadCondominiumRules(String token);

        void setView(View view);

        void validateRule(String regra, String token);

        void onDestroy();
    }

    interface Model {
        interface OnCondominiumRulesListener {

            void onSuccess(List<RuleResponse> ruleResponseList);

            void onServerError(String message);

            void onRegraError();

            void onCreateRuleSuccess();
        }

        void getCondominiumRules(String token, OnCondominiumRulesListener listener);

        void registerRegraSyndic(String regra, String token, final CondominiumRulesContract.Model.OnCondominiumRulesListener listener);
    }
}
