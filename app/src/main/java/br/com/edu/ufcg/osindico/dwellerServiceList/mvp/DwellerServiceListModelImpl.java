package br.com.edu.ufcg.osindico.dwellerServiceList.mvp;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.edu.ufcg.osindico.data.models.ServerResponse.ServiceRequestResponse;
import br.com.edu.ufcg.osindico.data.services.DwellerService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DwellerServiceListModelImpl implements DwellerServiceListContract.Model {

    private DwellerService dwellerService;

    public DwellerServiceListModelImpl(DwellerService dwellerService) {
        this.dwellerService = dwellerService;
    }

    @Override
    public void getDwellerServiceList(String token, final OnDwellerServiceListListener listener) {
        Call<List<ServiceRequestResponse>> call = dwellerService.getDwellerApi()
                .loadServiceRequests(token);

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
                listener.onServerError(t.getMessage());
            }
        });
    }
}
