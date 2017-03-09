package br.com.edu.ufcg.osindico.registerRegraSyndic.mvp;

import android.util.Log;

import java.io.IOException;
import java.lang.annotation.Annotation;

import br.com.edu.ufcg.osindico.Utils.FormValidate;
import br.com.edu.ufcg.osindico.data.models.RuleDetails;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.CondoServerResponse;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.RuleResponse;
import br.com.edu.ufcg.osindico.data.services.SyndicService;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

/**
 * Created by Lucio on 04/03/2017.
 */

public class RegisterRuleModelImpl implements RegisterRuleContract.Model {

    private SyndicService mSyndicService;

    public RegisterRuleModelImpl(SyndicService mSyndicService){
        this.mSyndicService = mSyndicService;

    }

       @Override
    public void registerRuleSyndic(String regra, String token, final OnRegisterRegraSyndicListener listener) {

        boolean error = false;

        if (!FormValidate.isValidName(regra)){
            listener.onRuleError();
            error = true;
            Log.d("deu erro:", "ERROOOOOOOOOOOU");
        }

        if(!error){
            Log.d("Nao:", "não deu erro");
            RuleDetails novaRegra = new RuleDetails(regra);
            Call<RuleResponse> mService = mSyndicService.getSyndicApi().registerRegra(token, novaRegra);

            mService.enqueue(new Callback<RuleResponse>() {
                @Override
                public void onResponse(Call<RuleResponse> call, Response<RuleResponse> response) {

                    if(response.isSuccessful()){
                        Log.d("Sucesso", "Regra criada");
                        listener.onSuccess();
                    }else{
                        Log.d("Falha", "Regra nao criada");
                        Log.d("status", Integer.toString(response.code()));
                        Converter<ResponseBody, CondoServerResponse> converter
                                = mSyndicService.getRetrofit().responseBodyConverter(
                                CondoServerResponse.class, new Annotation[0]);
                        CondoServerResponse errorResponse = null;

                        try {
                            errorResponse = converter.convert(response.errorBody());
                            listener.onServerError(errorResponse.getMessage());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                }

                @Override
                public void onFailure(Call<RuleResponse> call, Throwable t) {

                    call.cancel();
                    listener.onServerError(t.getMessage());

                }

            });

            Log.d("Criou uma nova regra", novaRegra.getRegraSyndic());

        }

    }
}
