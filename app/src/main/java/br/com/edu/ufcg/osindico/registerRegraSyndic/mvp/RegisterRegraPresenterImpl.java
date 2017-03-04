package br.com.edu.ufcg.osindico.registerRegraSyndic.mvp;

import br.com.edu.ufcg.osindico.data.services.SyndicService;
import br.com.edu.ufcg.osindico.data.services.ZipCodeService;
import br.com.edu.ufcg.osindico.registerCondo.mvp.RegisterCondoContract;
import br.com.edu.ufcg.osindico.registerCondo.mvp.RegisterCondoModelImpl;

/**
 * Created by Lucio on 04/03/2017.
 */

public class RegisterRegraPresenterImpl implements  RegisterRegraContract.Presenter, RegisterRegraContract.Model.OnRegisterRegraSyndicListener {

    private RegisterRegraContract.View view;
    private RegisterRegraContract.Model model;

    public RegisterRegraPresenterImpl(RegisterRegraContract.View view) {
        this.model = new RegisterRegraModelImpl();
        this.view = view;
    }




    @Override
    public void onRegraError() {

    }

    @Override
    public void onServerError(String message) {

    }

    @Override
    public void validateRegra(String regra) {

        if(view != null){
            this.model.registerRegraSyndic(regra, this);
        }


    }

    @Override
    public void setView(RegisterRegraContract.View view) {

    }

    @Override
    public void onDestroy() {

    }
}
