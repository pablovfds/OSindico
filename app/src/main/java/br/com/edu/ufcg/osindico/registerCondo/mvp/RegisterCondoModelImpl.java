package br.com.edu.ufcg.osindico.registerCondo.mvp;

import br.com.edu.ufcg.osindico.data.models.Address;
import br.com.edu.ufcg.osindico.data.models.CondoDetails;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.AddressResponse;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.CondoServerResponse;
import br.com.edu.ufcg.osindico.data.services.SyndicService;
import br.com.edu.ufcg.osindico.data.services.ZipCodeService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterCondoModelImpl implements RegisterCondoContract.Model {

    private SyndicService mSyndicService;
    private ZipCodeService mZipCodeService;

    public RegisterCondoModelImpl(SyndicService mSyndicService, ZipCodeService zipCodeService) {
        this.mSyndicService = mSyndicService;
        this.mZipCodeService = zipCodeService;
    }

    @Override
    public void register(String name, String street, int number, String complement,
                         String neighbor, String city, String zipCode, String state, Long syndicId,
                         final OnRegisterCondoListener listener) {
        Address addressItem = new Address(zipCode, street, complement, neighbor, city, state, number);

        CondoDetails condoModel = new CondoDetails(name, addressItem, syndicId);

        boolean error = false;

        if(condoModel.getName().isEmpty()){
            listener.onNameError();
            error = true;
        }

//        if(condoModel.getPhone().isEmpty()){
//            listener.onNameError();
//            error = true;
//        }


        if (condoModel.getAddress().getStreet().isEmpty()){
            listener.onStreetError();
            error = true;
        }

        if (condoModel.getAddress().getNeighbor().isEmpty()){
            listener.onNumberError();
            error = true;
        }

        if (condoModel.getAddress().getNumber() <= 0){
            listener.onNumberError();
            error = true;
        }

        if (condoModel.getAddress().getCity().isEmpty()){
            listener.onCityError();
            error = true;
        }
        if (condoModel.getAddress().getState().isEmpty()){
            listener.onStateError();
            error = true;
        }
        if (condoModel.getAddress().getZipCode().isEmpty()){
            listener.onZipCodeError();
            error = true;
        }

        if (error){
            Call<CondoServerResponse> mService = mSyndicService.getSyndicApi().registerCondo(condoModel);

            mService.enqueue(new Callback<CondoServerResponse>() {
                @Override
                public void onResponse(Call<CondoServerResponse> call, Response<CondoServerResponse> response) {
                    if (response.isSuccessful()) {
                        listener.onSuccess();
                    } else {
                        listener.onServerError();
                    }
                }

                @Override
                public void onFailure(Call<CondoServerResponse> call, Throwable t) {
                    call.cancel();
                    listener.onServerError();
                }
            });
        }

    }

    @Override
    public void loadAddressByZipCode(String zipcode, final OnLoadAddressFinishedListener listener) {
        Call<AddressResponse> mService = mZipCodeService.getZipCodeApi().getAddressByZipCode(zipcode);
        mService.enqueue(new Callback<AddressResponse>() {
                @Override
                public void onResponse(Call<AddressResponse> call, Response<AddressResponse> response) {
                    AddressResponse address = response.body();
                    listener.onSuccessGetAddress(address);
                }

                @Override
                public void onFailure(Call<AddressResponse> call, Throwable t) {
                    call.cancel();
                }
            });
    }
}
