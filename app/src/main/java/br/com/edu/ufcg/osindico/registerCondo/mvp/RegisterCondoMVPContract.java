package br.com.edu.ufcg.osindico.registerCondo.mvp;

import br.com.edu.ufcg.osindico.data.models.CondoDetails;
import br.com.edu.ufcg.osindico.data.models.SyndicDetails;

public interface RegisterCondoMVPContract {

    interface RegisterCondoModel {
        interface OnRegisterCondoFinishedListener {
            void onServerError();

            void onSuccess();
        }

        interface OnValidateCondoFinishedListener {
            void onNameError();

            void onPhoneError();

            void onAddressError();

            void onNumberError();

            void onCityError();

            void onZipCodeError();

            void onStateError();

            void onSuccessValidation(CondoDetails condoDetails);
        }

        void register(SyndicDetails syndicDetails, CondoDetails condoModel, OnRegisterCondoFinishedListener listener);

        void validateCredentialsCondo(CondoDetails condoModel, OnValidateCondoFinishedListener listener);
    }

    interface RegisterCondoPresenter {
        void validateCondoCredentials(String name, String phone, String address, int number,
                                      String city, String zipCode, String state);

        void onDestroy();
    }

    interface RegisterCondoView {
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


