package br.com.edu.ufcg.osindico.registerCondo.mvp;

import br.com.edu.ufcg.osindico.data.services.SyndicService;
import br.com.edu.ufcg.osindico.data.models.Address;
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
    public void register(String name, String phone, String street, int number, String complement,
                         String neighbor, String city, String zipCode, String state, Long syndicId,
                         OnRegisterCondoListener listener) {
        Address addressItem = new Address();
    }

    @Override
    public void loadAddressByZipCode(String zipcode, final OnLoadAddressFinishedListener listener) {
        Call<Address> mService = mZipCodeService.getZipCodeApi().getAddressByZipCode(zipcode);
        mService.enqueue(new Callback<Address>() {
                @Override
                public void onResponse(Call<Address> call, Response<Address> response) {
                    Address address = response.body();
                    listener.onSuccessGetAddress(address);
                }

                @Override
                public void onFailure(Call<Address> call, Throwable t) {
                    call.cancel();
                }
            });
    }

//    @Override
//    public void register(String name, String phone, String address, int number,
//                         String city, String zipCode, String state, Long syndicId,
//                         final OnRegisterCondoListener listener) {
//        Address addressItem = new Address(address, number, city, zipCode, state);
//        CondoDetails condoModel = new CondoDetails(name, addressItem, syndicId);
//
//        if(condoModel.getName().isEmpty()){
//            listener.onNameError();
//        } else if(condoModel.getAddress().getAddress().isEmpty()) {
//            listener.onAddressError();
//        } else if (condoModel.getAddress().getNumber() <= 0){
//            listener.onNumberError();
//        } else if (condoModel.getAddress().getCity().isEmpty()){
//            listener.onCityError();
//        } else if (condoModel.getAddress().getState().isEmpty()){
//            listener.onStateError();
//        } else if (condoModel.getAddress().getZipCode().isEmpty()){
//            listener.onZipCodeError();
//        } else {
//            Call<CondoServerResponse> mService = mSyndicService.getSyndicApi().registerCondo(condoModel);
//
//            mService.enqueue(new Callback<CondoServerResponse>() {
//                @Override
//                public void onResponse(Call<CondoServerResponse> call, Response<CondoServerResponse> response) {
//                    listener.onSuccess();
//                }
//
//                @Override
//                public void onFailure(Call<CondoServerResponse> call, Throwable t) {
//                    call.cancel();
//                    listener.onServerError();
//                }
//            });
//        }
//
//
    //}
}
