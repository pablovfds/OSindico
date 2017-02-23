package br.com.edu.ufcg.osindico.requests_residents.mvp;


import android.util.Log;

import java.util.List;

import br.com.edu.ufcg.osindico.data.models.ServerResponse.ResidentResponse;
import br.com.edu.ufcg.osindico.data.services.SyndicService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RequestsResidentsModelImpl implements RequestsResidentsContract.Model {
    private SyndicService syndicService;

    public RequestsResidentsModelImpl(SyndicService syndicService) {
        this.syndicService = syndicService;
    }

    @Override
    public void getRequestsResidents(String token, final OnRequestsResidentsListener listener) {
        Call<List<ResidentResponse>> call = syndicService.getSyndicApi()
                .loadRequestsResidents(token);

        call.enqueue(new Callback<List<ResidentResponse>>() {
            @Override
            public void onResponse(Call<List<ResidentResponse>> call,
                                   Response<List<ResidentResponse>> response) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<ResidentResponse>> call, Throwable t) {
                Log.d("onFailure", t.toString());
                listener.onServerError(t.getMessage());
            }
        });
    }
}
