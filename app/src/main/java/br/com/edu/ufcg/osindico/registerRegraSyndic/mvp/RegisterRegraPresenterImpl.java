package br.com.edu.ufcg.osindico.registerRegraSyndic.mvp;


import br.com.edu.ufcg.osindico.data.services.SyndicService;

/**
 * Created by Lucio on 04/03/2017.
 */

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
