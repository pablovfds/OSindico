package br.com.edu.ufcg.osindico.dwellerDetails.mvp;

import com.google.gson.Gson;
import java.io.IOException;

import br.com.edu.ufcg.osindico.data.models.ServerResponse.MessageResponse;
import br.com.edu.ufcg.osindico.data.services.SyndicService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DwellerDetailsModelImpl implements DwellerDetailsContract.Model {

    private SyndicService syndicService;

    public DwellerDetailsModelImpl(SyndicService service) {
        this.syndicService = service;
    }

    @Override
    public void sendResponseRequest(String token, Long id, boolean status, final OnDwellerDetailsListener listener) {
        Call<MessageResponse> call = syndicService.getSyndicApi().updateResidentStatus(token, id, status);

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
                call.cancel();
                listener.onServerError("Erro ao tentar se conectar ao servidor.");
            }
        });
    }
}
