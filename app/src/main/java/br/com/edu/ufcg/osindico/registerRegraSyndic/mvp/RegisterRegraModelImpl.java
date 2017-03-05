package br.com.edu.ufcg.osindico.registerRegraSyndic.mvp;

import android.content.SharedPreferences;
import android.util.Log;

import java.io.IOException;
import java.lang.annotation.Annotation;

import br.com.edu.ufcg.osindico.Utils.FormValidate;
import br.com.edu.ufcg.osindico.data.models.RuleDetails;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.CondoServerResponse;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.DwellerServerResponse;
import br.com.edu.ufcg.osindico.data.services.SyndicService;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

/**
 * Created by Lucio on 04/03/2017.
 */

public class RegisterRegraModelImpl implements RegisterRegraContract.Model {

    private SyndicService mSyndicService;

    public RegisterRegraModelImpl(SyndicService mSyndicService){
        this.mSyndicService = mSyndicService;

    }

       @Override
    public void registerRegraSyndic(String regra, String token, final OnRegisterRegraSyndicListener listener) {

        boolean error = false;

        if (!FormValidate.isValidName(regra)){
            listener.onRegraError();
            error = true;
            Log.d("deu erro:", "ERROOOOOOOOOOOU");
        }

        if(!error){
            Log.d("Nao:", "não deu erro");
            RuleDetails novaRegra = new RuleDetails(regra);
            Call<DwellerServerResponse> mService = mSyndicService.getSyndicApi().registerRegra(token, novaRegra);

            mService.enqueue(new Callback<DwellerServerResponse>() {
                @Override
                public void onResponse(Call<DwellerServerResponse> call, Response<DwellerServerResponse> response) {

                    if(response.isSuccessful()){
                        listener.onSuccess();
                    }else{

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
                public void onFailure(Call<DwellerServerResponse> call, Throwable t) {

                    call.cancel();
                    listener.onServerError(t.getMessage());

                }

            });

            Log.d("Criou uma nova regra", novaRegra.getRegraSyndic());

        }

    }
}
