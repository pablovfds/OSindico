package br.com.edu.ufcg.osindico.registerRegraSyndic.mvp;

import br.com.edu.ufcg.osindico.data.services.SyndicService;

public class RegisterRulePresenterImpl implements  RegisterRuleContract.Presenter, RegisterRuleContract.Model.OnRegisterRegraSyndicListener {

    private RegisterRuleContract.View view;
    private RegisterRuleContract.Model model;

    public RegisterRulePresenterImpl(RegisterRuleContract.View view, SyndicService mSyndicService) {
        this.model = new RegisterRuleModelImpl(mSyndicService);
        this.view = view;
    }

    @Override
    public void onRuleError() {

        if (view != null) {
            view.setRuleError();
        }

    }

    @Override
    public void onServerError(String message) {
        if (view != null) {
            view.setServerError(message);
        }
    }

    @Override
    public void onSuccess() {
        if(view != null){
            this.view.navigateToRulesCondoList();
        }
    }

    @Override
    public void validateRule(String regra, String token) {

        if(view != null){
            this.model.registerRuleSyndic(regra, token, this);
        }


    }

    @Override
    public void setView(RegisterRuleContract.View view) {
        this.view = view;
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }
}
