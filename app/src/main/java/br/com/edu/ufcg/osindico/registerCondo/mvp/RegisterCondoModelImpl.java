package br.com.edu.ufcg.osindico.registerCondo.mvp;

import br.com.edu.ufcg.osindico.data.models.CondoDetails;
import br.com.edu.ufcg.osindico.data.models.CondoServerRequest;
import br.com.edu.ufcg.osindico.data.models.CondoServerResponse;
import br.com.edu.ufcg.osindico.data.models.SyndicDetails;
import br.com.edu.ufcg.osindico.data.models.SyndicServerRequest;
import br.com.edu.ufcg.osindico.data.models.SyndicServerResponse;
import br.com.edu.ufcg.osindico.data.services.SyndicService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterCondoModelImpl implements RegisterCondoContract.Model {

    SyndicService mSyndicService;

    public RegisterCondoModelImpl(SyndicService mSyndicService) {
        this.mSyndicService = mSyndicService;
    }

    @Override
    public void register(SyndicDetails syndicDetails, CondoDetails condoModel,
                         final OnRegisterCondoListener listener) {
        CondoServerRequest request = new CondoServerRequest();
        request.setCondoDetails(condoModel);
        Call<CondoServerResponse> mService = mSyndicService.getSyndicApi().registerCondo(request);

        mService.enqueue(new Callback<CondoServerResponse>() {
            @Override
            public void onResponse(Call<CondoServerResponse> call, Response<CondoServerResponse> response) {
                listener.onSuccess();
            }

            @Override
            public void onFailure(Call<CondoServerResponse> call, Throwable t) {
                call.cancel();
                listener.onServerError();
            }
        });
    }

    @Override
    public void validateCredentialsCondo(CondoDetails condoModel, OnValidateCondoListener listener) {
        if(condoModel.getName().isEmpty()){
            listener.onNameError();
        } else if(condoModel.getAddress().getAddress().isEmpty()) {
            listener.onAddressError();
        } else if(condoModel.getPhone().getPhoneNumber().isEmpty()) {
            listener.onPhoneError();
        } else if (condoModel.getAddress().getNumber() <= 0){
            listener.onNumberError();
        } else if (condoModel.getAddress().getCity().isEmpty()){
            listener.onCityError();
        } else if (condoModel.getAddress().getState().isEmpty()){
            listener.onStateError();
        } else if (condoModel.getAddress().getZipCode().isEmpty()){
            listener.onZipCodeError();
        } else {
            listener.onSuccessValidation(condoModel);
        }
    }
}
