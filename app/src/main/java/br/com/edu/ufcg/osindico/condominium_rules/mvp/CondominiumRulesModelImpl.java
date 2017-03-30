package br.com.edu.ufcg.osindico.condominium_rules.mvp;

import android.util.Log;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import br.com.edu.ufcg.osindico.Utils.FormValidate;
import br.com.edu.ufcg.osindico.data.models.RuleDetails;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.MessageResponse;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.RuleResponse;
import br.com.edu.ufcg.osindico.data.services.RulesService;
import br.com.edu.ufcg.osindico.data.services.SyndicService;
import br.com.edu.ufcg.osindico.registerRegraSyndic.mvp.RegisterRegraContract;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

/**
 * Created by emanoel on 02/03/17.
 */

public class CondominiumRulesModelImpl implements CondominiumRulesContract.Model {

    private RulesService rulesService;
    private SyndicService mSyndicService;


    public CondominiumRulesModelImpl(SyndicService service) {
        this.mSyndicService = service;
    }

    @Override
    public void getCondominiumRules(String token, final OnCondominiumRulesListener listener) {
        Call<List<RuleResponse>> call = mSyndicService.getSyndicApi().getCondominiumRules(token);

        call.enqueue(new Callback<List<RuleResponse>>() {
            @Override
            public void onResponse(Call<List<RuleResponse>> call, Response<List<RuleResponse>> response) {
                List<RuleResponse> ruleResponseList = response.body();

                if (response.isSuccessful()) {
                    Log.d("Sucesso", "OK");
                    listener.onSuccess(ruleResponseList);
                } else {
                    Log.d("status", Integer.toString(response.code()));
                    listener.onSuccess(new ArrayList<RuleResponse>());
                }
            }

            @Override
            public void onFailure(Call<List<RuleResponse>> call, Throwable t) {
                Log.d("onFailure", t.toString());
                listener.onServerError(t.getMessage());
            }
        });
    }

    @Override
    public void registerRegraSyndic(String regra, String token, final OnCondominiumRulesListener listener) {
        boolean error = false;

        if (!FormValidate.isValidName(regra)){
            listener.onRegraError();
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
                        Log.e("Sucesso", "Regra criada");
                        listener.onCreateRuleSuccess();
                    }else{
                        Log.d("Falha", "Regra nao criada");
                        Log.d("status", Integer.toString(response.code()));
                        Converter<ResponseBody, MessageResponse> converter
                                = mSyndicService.getRetrofit().responseBodyConverter(
                                MessageResponse.class, new Annotation[0]);
                        MessageResponse errorResponse = null;

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
