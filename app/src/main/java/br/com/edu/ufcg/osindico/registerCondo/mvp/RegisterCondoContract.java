package br.com.edu.ufcg.osindico.registerCondo.mvp;

import br.com.edu.ufcg.osindico.data.models.CondoDetails;
import br.com.edu.ufcg.osindico.data.models.SyndicDetails;

public interface RegisterCondoContract {

    interface Model {
        interface OnRegisterCondoListener {

            void onNameError();

            void onAddressError();

            void onNumberError();

            void onCityError();

            void onZipCodeError();

            void onStateError();
            void onServerError();

            void onSuccess();
        }

        void register(String name, String phone, String address, int number,
                      String city, String zipCode, String state, String syndicId, OnRegisterCondoListener listener);
    }

    interface Presenter {
        void validateCondoCredentials(String name, String phone, String address, int number,
                                      String city, String zipCode, String state, String syndicId);

        void onDestroy();
    }

    interface View {
        void showProgress();

        void hideProgress();

        void setNameError();

        void setPhoneError();

        void setAddressError();

        void setNumberError();

        void setCityError();

        void setZipCodeError();

        void setStateError();

        void navigateToLogin();

        void navigateToRegisterSyndic(CondoDetails condoDetails);

        void setServerError();
    }
}


