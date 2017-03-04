package br.com.edu.ufcg.osindico.registerRegraSyndic.mvp;

import android.util.Log;

import br.com.edu.ufcg.osindico.Utils.FormValidate;
import br.com.edu.ufcg.osindico.data.models.RuleDetails;
import br.com.edu.ufcg.osindico.data.services.SyndicService;

/**
 * Created by Lucio on 04/03/2017.
 */

public class RegisterRegraModelImpl implements RegisterRegraContract.Model {





    @Override
    public void registerRegraSyndic(String regra, OnRegisterRegraSyndicListener listener) {

        boolean error = false;

        if (!FormValidate.isValidName(regra)){
            listener.onRegraError();
            error = true;
        }

        if(!error){
            RuleDetails novaRegra = new RuleDetails(regra);

            Log.d("Criou uma nova regra", novaRegra.getRegraSyndic());

            // fazer o resto aqui


        }

    }
}
