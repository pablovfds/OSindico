package br.com.edu.ufcg.osindico.residentDetails.mvp;

import com.google.gson.Gson;
import java.io.IOException;
import br.com.edu.ufcg.osindico.data.models.MoradorServerResponse;
import br.com.edu.ufcg.osindico.data.services.SyndicService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResidentDetailsModelImpl implements ResidentDetailsContract.Model {

    private SyndicService syndicService;

    public ResidentDetailsModelImpl(SyndicService service) {
        this.syndicService = service;
    }

    @Override
    public void sendResponseRequest(String token, Long id, boolean status, final OnResidentDetailsListener listener) {
        Call<MoradorServerResponse> call = syndicService.getSyndicApi().updateResidentStatus(token, id, status);

        call.enqueue(new Callback<MoradorServerResponse>() {
            @Override
            public void onResponse(Call<MoradorServerResponse> call, Response<MoradorServerResponse> response) {
                if (response.isSuccessful()){
                    listener.onSuccess();
                } else {
                    Gson gson = new Gson();
                    MoradorServerResponse serverResponse = null;
                    try {
                        serverResponse = gson.fromJson(response.errorBody().string(), MoradorServerResponse.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (serverResponse != null)
                        listener.onServerError(serverResponse.getMessage());
                }
            }

            @Override
            public void onFailure(Call<MoradorServerResponse> call, Throwable t) {
                call.cancel();
                listener.onServerError("Erro ao tentar se conectar ao servidor.");
            }
        });
    }
}
