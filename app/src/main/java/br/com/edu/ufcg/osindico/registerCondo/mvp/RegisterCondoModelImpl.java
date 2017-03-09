package br.com.edu.ufcg.osindico.registerCondo.mvp;

import java.io.IOException;
import java.lang.annotation.Annotation;

import br.com.edu.ufcg.osindico.data.models.Address;
import br.com.edu.ufcg.osindico.data.models.CondoDetails;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.AddressResponse;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.MessageResponse;
import br.com.edu.ufcg.osindico.data.services.SyndicService;
import br.com.edu.ufcg.osindico.data.services.ZipCodeService;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
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

        if (condoModel.getName().isEmpty()) {
            listener.onNameError();
            error = true;
        }

        if (condoModel.getAddress().getStreet().isEmpty()) {
            listener.onStreetError();
            error = true;
        }

        if (condoModel.getAddress().getNeighbor().isEmpty()) {
            listener.onNeighborError();
            error = true;
        }

        if (condoModel.getAddress().getNumber() < 0) {
            listener.onNumberError();
            error = true;
        }

        if (condoModel.getAddress().getCity().isEmpty()) {
            listener.onCityError();
            error = true;
        }
        if (condoModel.getAddress().getState().isEmpty()) {
            listener.onStateError();
            error = true;
        }
        if (condoModel.getAddress().getZipCode().isEmpty()) {
            listener.onZipCodeError();
            error = true;
        }

        if (!error) {
            Call<MessageResponse> mService = mSyndicService.getSyndicApi().registerCondo(condoModel);

            mService.enqueue(new Callback<MessageResponse>() {
                @Override
                public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                    if (response.isSuccessful()) {
                        listener.onSuccess();
                    } else {
                        Converter<ResponseBody, MessageResponse> converter
                                = mSyndicService.getRetrofit().responseBodyConverter(
                                MessageResponse.class, new Annotation[0]);
                        MessageResponse errorResponse = null;
                        try {
                            errorResponse = converter.convert(response.errorBody());
                            listener.onServerError(errorResponse.getMessage());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<MessageResponse> call, Throwable t) {
                    call.cancel();
                    listener.onServerError(t.getMessage());
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
