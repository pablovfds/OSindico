package br.com.edu.ufcg.osindico.condominium_rules.mvp;

import java.util.List;

import br.com.edu.ufcg.osindico.data.models.ServerResponse.RuleResponse;
import br.com.edu.ufcg.osindico.data.services.RulesService;

/**
 * Created by emanoel on 02/03/17.
 */

public class CondominiumRulesPresenterImpl implements CondominiumRulesContract.Presenter, CondominiumRulesContract.Model.OnCondominiumRulesListener {

    private CondominiumRulesContract.View view;
    private CondominiumRulesContract.Model model;

    public CondominiumRulesPresenterImpl(RulesService service) {
        model = new CondominiumRulesModelImpl(service);
    }

    @Override
    public void loadCondominiumRules(String token) {
        if (view != null) {
            view.showProgress();
            model.getCondominiumRules(token, this);
        }
    }

    @Override
    public void onSuccess(List<RuleResponse> ruleResponseList) {
        if (view != null) {
            view.hideProgress();
            view.setCondominiumRulesList(ruleResponseList);
        }
    }

    @Override
    public void onServerError(String message) {
        if (view != null) {
            view.hideProgress();
            view.setServerError(message);
        }
    }

    @Override
    public void setView(CondominiumRulesContract.View newView) {
        if (newView != null)
            view = newView;
    }

    @Override
    public void onDestroy() {
        view = null;
    }
}
