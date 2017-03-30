package br.com.edu.ufcg.osindico.condominiumResidents.mvp;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.edu.ufcg.osindico.data.models.ServerResponse.DwellerResponse;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.ServiceRequestResponse;
import br.com.edu.ufcg.osindico.data.services.SyndicService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Cathy on 25/03/2017.
 */

public class CondominiumResidentsModelImpl implements CondominiumResidentsContract.Model {

    SyndicService service;

    public CondominiumResidentsModelImpl(SyndicService service){
        this.service = service;
    }

    @Override
    public void getCondominiumResidents(String token, final OnCondominiumResidentsListener listener) {
        if(token == null || token.isEmpty()){
            Log.e("Token error", "Invalid token");
        } else {
            Call<List<DwellerResponse>> call = service.getSyndicApi()
                    .loadResidentsList(token);

            call.enqueue(new Callback<List<DwellerResponse>>() {
                @Override
                public void onResponse(Call<List<DwellerResponse>> call,
                                       Response<List<DwellerResponse>> response) {

                    List<DwellerResponse> responses = response.body();
                    Log.e("response list", response.code() + " " + response.body());

                    if (response.isSuccessful() && responses != null){
                        Log.d("size", String.valueOf(responses.size()));
                        listener.onSuccess(responses);
                    } else {
                        Log.d("size", "0");
                        listener.onSuccess(new ArrayList<DwellerResponse>());
                    }
                }

                @Override
                public void onFailure(Call<List<DwellerResponse>> call, Throwable t) {
                    Log.d("onFailure", t.toString());
                    listener.onServerError(t.getMessage());
                }
            });
        }
    }
}
