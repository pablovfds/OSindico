package br.com.edu.ufcg.osindico.registerRegraSyndic.mvp;

import br.com.edu.ufcg.osindico.data.services.SyndicService;

public class RegisterRegraPresenterImpl implements  RegisterRegraContract.Presenter, RegisterRegraContract.Model.OnRegisterRegraSyndicListener {

    private RegisterRegraContract.View view;
    private RegisterRegraContract.Model model;

    public RegisterRegraPresenterImpl(RegisterRegraContract.View view, SyndicService mSyndicService) {
        this.model = new RegisterRegraModelImpl(mSyndicService);
        this.view = view;
    }

    @Override
    public void onRegraError() {

    }

    @Override
    public void onServerError(String message) {

    }

    @Override
    public void onSuccess() {
        if(view != null){
            this.view.navigateToRulesCondoList();
        }
    }

    @Override
    public void validateRegra(String regra, String token) {

        if(view != null){
            this.model.registerRegraSyndic(regra, token, this);
        }


    }

    @Override
    public void setView(RegisterRegraContract.View view) {

    }

    @Override
    public void onDestroy() {

    }
}
