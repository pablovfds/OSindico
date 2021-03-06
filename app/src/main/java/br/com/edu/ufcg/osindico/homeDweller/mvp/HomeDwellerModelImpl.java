package br.com.edu.ufcg.osindico.homeDweller.mvp;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.edu.ufcg.osindico.data.models.ServerResponse.MessageResponse;
import br.com.edu.ufcg.osindico.data.services.DwellerService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeDwellerModelImpl implements HomeDwellerContract.Model {

    private DwellerService dwellerService;

    public HomeDwellerModelImpl(DwellerService dwellerService) {
        this.dwellerService = dwellerService;
    }

    @Override
    public void getMessages(String token, final HomeDwellerContract.Model.HomeDwellerListener listener) {

        Call<List<MessageResponse>> call = dwellerService.getDwellerApi()
                .loadMessageRequests(token);

        call.enqueue(new Callback<List<MessageResponse>>() {
            @Override
            public void onResponse(Call<List<MessageResponse>> call,
                                   Response<List<MessageResponse>> response) {

                List<MessageResponse> messageResponses = response.body();

                if (response.isSuccessful() && messageResponses != null){
                    listener.onLoadMessagesSuccess(messageResponses);
                } else {
                    listener.onLoadMessagesSuccess(new ArrayList<MessageResponse>());
                }
            }

            @Override
            public void onFailure(Call<List<MessageResponse>> call, Throwable t) {
                Log.e("message response", "error");
                listener.onServerError(t.getMessage());
            }
        });
    }
}
