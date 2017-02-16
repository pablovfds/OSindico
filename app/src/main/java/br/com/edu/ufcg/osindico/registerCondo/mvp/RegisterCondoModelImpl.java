package br.com.edu.ufcg.osindico.registerCondo.mvp;

import br.com.edu.ufcg.osindico.data.models.CondoDetails;
import br.com.edu.ufcg.osindico.data.models.SyndicDetails;
import br.com.edu.ufcg.osindico.data.services.SyndicService;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class RegisterCondoModelImpl implements RegisterCondoMVPContract.RegisterCondoModel {

    SyndicService mSyndicService;

    public RegisterCondoModelImpl(SyndicService mSyndicService) {
        this.mSyndicService = mSyndicService;
    }

    @Override
    public void register(SyndicDetails syndicDetails, CondoDetails condoModel,
                         OnRegisterCondoFinishedListener listener) {
        mSyndicService.getSyndicApi()
                .registerSyndic(syndicDetails, condoModel, new Callback<Response>() {

                    @Override
                    public void success(Response response, Response response2) {

                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });
    }

    @Override
    public void validateCredentialsCondo(CondoDetails condoModel, OnValidateCondoFinishedListener listener) {
        if(condoModel.getName().isEmpty()){
            listener.onNameError();
        } else if(condoModel.getPhone().isEmpty()){
            listener.onPhoneError();
        } else if (condoModel.getAddress().getStreet().isEmpty()){
            listener.onStreetError();
        } else if (condoModel.getAddress().getNumber() <= 0){
            listener.onNumberError();
        } else if (condoModel.getAddress().getCity().isEmpty()){
            listener.onCityError();
        } else if (condoModel.getAddress().getNeighborhood().isEmpty()){
            listener.onNeighborhoodError();
        } else if (condoModel.getAddress().getState().isEmpty()){
            listener.onStateError();
        } else if (condoModel.getAddress().getCountry().isEmpty()){
            listener.onCountyError();
        } else if (condoModel.getAddress().getZipCode().isEmpty()){
            listener.onZipCodeError();
        } else {
            listener.onSuccess();
        }
    }
}
