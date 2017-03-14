package br.com.edu.ufcg.osindico.dwellerRequests.mvp;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.edu.ufcg.osindico.data.models.ServerResponse.DwellerResponse;
import br.com.edu.ufcg.osindico.data.services.SyndicService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestsResidentsModelImpl implements RequestsResidentsContract.Model {
    private SyndicService syndicService;

    public RequestsResidentsModelImpl(SyndicService syndicService) {
        this.syndicService = syndicService;
    }

    @Override
    public void getRequestsResidents(String token, final OnRequestsResidentsListener listener) {
        Call<List<DwellerResponse>> call = syndicService.getSyndicApi()
                .loadRequestsResidents(token);

        call.enqueue(new Callback<List<DwellerResponse>>() {
            @Override
            public void onResponse(Call<List<DwellerResponse>> call,
                                   Response<List<DwellerResponse>> response) {

                List<DwellerResponse> dwellerResponses = response.body();

                if (response.isSuccessful() && dwellerResponses != null){
                    listener.onSuccess(dwellerResponses);
                } else {
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
