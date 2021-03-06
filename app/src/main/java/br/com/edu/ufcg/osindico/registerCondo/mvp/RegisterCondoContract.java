package br.com.edu.ufcg.osindico.registerCondo.mvp;

import br.com.edu.ufcg.osindico.base.BasePresenter;
import br.com.edu.ufcg.osindico.base.BaseView;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.AddressResponse;

public interface RegisterCondoContract {

    interface Model {
        interface OnRegisterCondoListener {
            void onNameError();

            void onStreetError();

            void onNumberError();

            void onCityError();

            void onZipCodeError();

            void onStateError();

            void onNeighborError();

            void onServerError(String message);

            void onSuccess();
        }

        interface OnLoadAddressFinishedListener {
            void onSuccessGetAddress(AddressResponse address);
        }

        void register(String name, String street, int number,
                      String complement,String neighbor,String city, String zipCode,
                      String state, Long syndicId, OnRegisterCondoListener listener);

        void loadAddressByZipCode(String zipcode, OnLoadAddressFinishedListener listener);
    }

    interface Presenter extends BasePresenter{
        void validateCondoCredentials(String name, String street, int number,
                                      String complement,String neighbor,String city, String zipCode,
                                      String state, Long syndicId);

        void getAddressByZipCode(String zipcode);
    }

    interface View extends BaseView {
        void setNameError();

        void setNumberError();

        void setCityError();

        void setZipCodeError();

        void setStateError();

        void setStreetError();

        void setNeighborError();

        void navigateToLogin();

        void setServerError(String message);

        void setAddressDataViews(AddressResponse address);
    }
}


