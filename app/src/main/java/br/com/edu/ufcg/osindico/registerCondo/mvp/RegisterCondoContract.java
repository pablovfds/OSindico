package br.com.edu.ufcg.osindico.registerCondo.mvp;

import br.com.edu.ufcg.osindico.data.models.CondoDetails;
import br.com.edu.ufcg.osindico.data.models.SyndicDetails;

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

            void onComplementError();

            void onServerError();

            void onSuccess();
        }

        void register(String name, String phone, String street, int number,
                      String complement,String neighbor,String city, String zipCode,
                      String state, Long syndicId, OnRegisterCondoListener listener);
    }

    interface Presenter {
        void validateCondoCredentials(String name, String phone, String street, int number,
                                      String complement,String neighbor,String city, String zipCode,
                                      String state, Long syndicId);

        void onDestroy();
    }

    interface View {
        void showProgress();

        void hideProgress();

        void setNameError();

        void setNumberError();

        void setCityError();

        void setZipCodeError();

        void setStateError();

        void setStreetError();

        void setNeighborError();

        void setComplementError();

        void navigateToLogin();

        void setServerError();
    }
}


