package br.com.edu.ufcg.osindico.registerCondo.mvp;

import br.com.edu.ufcg.osindico.data.models.CondoDetails;
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
        SyndicServerRequest request = new SyndicServerRequest();
        request.setCondoDetails(condoModel);
        request.setSyndicDetails(syndicDetails);
        Call<SyndicServerResponse> mService = mSyndicService.getSyndicApi().registerSyndic(request);

        mService.enqueue(new Callback<SyndicServerResponse>() {
            @Override
            public void onResponse(Call<SyndicServerResponse> call, Response<SyndicServerResponse> response) {
                listener.onSuccess();
            }

            @Override
            public void onFailure(Call<SyndicServerResponse> call, Throwable t) {
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
