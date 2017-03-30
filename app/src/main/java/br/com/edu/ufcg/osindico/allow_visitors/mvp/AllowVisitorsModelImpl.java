package br.com.edu.ufcg.osindico.allow_visitors.mvp;

import android.util.Log;

import java.util.List;

import br.com.edu.ufcg.osindico.data.models.ServerResponse.MessageResponse;
import br.com.edu.ufcg.osindico.data.models.VisitorDetails;
import br.com.edu.ufcg.osindico.data.models.VisitorsList;
import br.com.edu.ufcg.osindico.data.services.DwellerService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllowVisitorsModelImpl implements AllowVisitorsContract.Model {

    private DwellerService service;

    public AllowVisitorsModelImpl(DwellerService service) {
        this.service = service;
    }

    @Override
    public void registerVisitorsList(String token, String date, List<VisitorDetails> visitorDetails, final AllowVisitorsContract.Presenter listener) {
        Call<MessageResponse> call = service.getDwellerApi().sendVisitorsList(token, new VisitorsList(date, visitorDetails));

        call.enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                if (response.isSuccessful()) {
                    listener.onSuccess();
                } else {
                    listener.onServerError(response.message());
                }
            }

            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {
                call.cancel();
                Log.d("Erro", t.getMessage());
            }
        });
    }
}
