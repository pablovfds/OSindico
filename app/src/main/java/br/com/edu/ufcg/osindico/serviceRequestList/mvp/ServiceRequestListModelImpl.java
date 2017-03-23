package br.com.edu.ufcg.osindico.serviceRequestList.mvp;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.edu.ufcg.osindico.data.models.ServerResponse.MessageResponse;
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
                        Log.d("size", String.valueOf(responses.size()));
                        listener.onLoadListSuccess(responses);
                    } else {
                        Log.d("size", "0");
                        listener.onLoadListSuccess(new ArrayList<ServiceRequestResponse>());
                    }

                  //  Log.d("size", String.valueOf(responses.size()));
                }

                @Override
                public void onFailure(Call<List<ServiceRequestResponse>> call, Throwable t) {
                    Log.d("onFailure", t.toString());
                    listener.onServerError(t.getMessage());
                }
            });
        }
    }

    @Override
    public void updateServiceRequestStatus(String token, Long id, final OnServiceRequestListListener listener) {
        Call<MessageResponse> call = syndicService.getSyndicApi().updateServiceRequestStatus(token, id);

        call.enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                MessageResponse messageResponse = response.body();
                if (response.isSuccessful()){
                    listener.onUpdateStatusSuccess(messageResponse.getMessage());
                } else {
                    Gson gson = new Gson();
                    MessageResponse serverResponse = null;
                    try {
                        serverResponse = gson.fromJson(response.errorBody().string(), MessageResponse.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (serverResponse != null)
                        listener.onServerError(serverResponse.getMessage());
                }
            }

            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {
                call.cancel();
                listener.onServerError("Erro ao tentar se conectar ao servidor.");
            }
        });
    }
}
