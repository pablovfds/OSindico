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

            void onStreetError();

            void onNumberError();

            void onNeighborhoodError();

            void onCityError();

            void onZipCodeError();

            void onStateError();

            void onCountyError();

            void onSuccess();
        }

        void register(SyndicDetails syndicDetails, CondoDetails condoModel, OnRegisterCondoFinishedListener listener);

        void validateCredentialsCondo(CondoDetails condoModel, OnValidateCondoFinishedListener listener);
    }

    interface RegisterCondoPresenter {
        void validateCondoCredentials(String name, String phone, String street, int number,
                                 String neighborhood, String city, String zipCode, String state,
                                 String country);

        void setView(RegisterCondoView view);

        void onDestroy();
    }

    interface RegisterCondoView {
        void showProgress();

        void hideProgress();

        void setUsernameError();

        void setPasswordError();

        void navigateToHome();
    }
}


