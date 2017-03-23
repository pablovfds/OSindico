package br.com.edu.ufcg.osindico.request_service.mvp;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import br.com.edu.ufcg.osindico.base.BaseListener;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.MessageResponse;
import br.com.edu.ufcg.osindico.data.models.ServiceRequest;
import br.com.edu.ufcg.osindico.data.services.DwellerService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestServiceModelImpl implements RequestServiceContract.Model {

    private DwellerService dwellerService;

    public RequestServiceModelImpl(DwellerService service) {
        this.dwellerService = service;
    }

    @Override
    public void sendRequest(String token, final ServiceRequest request, final BaseListener listener) {
        Call<MessageResponse> call = dwellerService.getDwellerApi()
                .sendServiceRequest(token, request);
        Log.e("token", token);
        call.enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                if (response.isSuccessful()){
                    listener.onSuccess();
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
                Log.e("service", "service");
            }
        });
    }
}