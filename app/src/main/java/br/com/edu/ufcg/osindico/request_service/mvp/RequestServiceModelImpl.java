package br.com.edu.ufcg.osindico.request_service.mvp;

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

            }
        });
    }
/*
    @Override
    public boolean validateData(String token, String title, String type, String description, OnRequestServiceListener listener) {
        boolean error = false;
        if (token == null || token.trim().isEmpty()){
            listener.onTokenError();
            error = true;
        } else if (title == null || title.trim().isEmpty() || title.length() < 5){
            listener.onTitleError();
            error = true;
        } else if (type == null || type.trim().isEmpty()){
            listener.onTypeError();
            error = true;
        } else if (description == null || description.trim().isEmpty() || title.length() < 10){
            listener.onTypeError();
            error = true;
        }
        return error;
    }
*/
}