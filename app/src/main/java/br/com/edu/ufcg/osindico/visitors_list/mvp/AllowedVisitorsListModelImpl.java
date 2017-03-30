package br.com.edu.ufcg.osindico.visitors_list.mvp;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import br.com.edu.ufcg.osindico.data.models.ServerResponse.MessageResponse;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.VisitorResponse;
import br.com.edu.ufcg.osindico.data.services.SyndicService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllowedVisitorsListModelImpl implements AllowedVisitorsListContract.Model {

    private SyndicService syndicService;

    public AllowedVisitorsListModelImpl(SyndicService service) {
        this.syndicService = service;
    }

    @Override
    public void getAllowedVisitorsList(String token, final OnAllowedVisitorsListListener listener) {
        Call<List<VisitorResponse>> call = syndicService.getSyndicApi().loadDwellerVisitors(token);

        call.enqueue(new Callback<List<VisitorResponse>>() {
            @Override
            public void onResponse(Call<List<VisitorResponse>> call, Response<List<VisitorResponse>> response) {
                List<VisitorResponse> responseList = response.body();

                if (response.isSuccessful()){
                    listener.onSuccess(responseList);
                } else {
                    Gson gson = new Gson();
                    try {
                        MessageResponse serverResponse = gson.fromJson(response.errorBody().string(), MessageResponse.class);
                        if (serverResponse != null) {
                            listener.onServerError(serverResponse.getMessage());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<VisitorResponse>> call, Throwable t) {
                call.cancel();
                listener.onServerError("Erro ao tentar se conectar ao servidor.");
            }
        });
    }
}
