package br.com.edu.ufcg.osindico.serviceRequestList.mvp;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.edu.ufcg.osindico.data.models.ServerResponse.ServiceRequestResponse;
import br.com.edu.ufcg.osindico.data.services.SyndicService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceRequestListModelImpl implements ServiceRequestListContract.Model {

    private SyndicService syndicService;

    public ServiceRequestListModelImpl(SyndicService syndicService) {
        this.syndicService = syndicService;
    }

    @Override
    public void loadServicesList(String token, final OnServiceRequestListListener listener) {
        if(token == null || token.isEmpty()){
            listener.onTokenError();
        } else {
            Call<List<ServiceRequestResponse>> call = syndicService.getSyndicApi()
                    .loadServiceRequestsResidents(token);

            call.enqueue(new Callback<List<ServiceRequestResponse>>() {
                @Override
                public void onResponse(Call<List<ServiceRequestResponse>> call,
                                       Response<List<ServiceRequestResponse>> response) {

                    List<ServiceRequestResponse> responses = response.body();

                    if (response.isSuccessful() && responses != null){
                        listener.onSuccess(responses);
                    } else {
                        listener.onSuccess(new ArrayList<ServiceRequestResponse>());
                    }
                }

                @Override
                public void onFailure(Call<List<ServiceRequestResponse>> call, Throwable t) {
                    Log.d("onFailure", t.toString());
                    listener.onServerError(t.getMessage());
                }
            });
        }
    }
}
