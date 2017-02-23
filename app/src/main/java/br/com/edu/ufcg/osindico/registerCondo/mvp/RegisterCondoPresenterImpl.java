package br.com.edu.ufcg.osindico.registerCondo.mvp;

import br.com.edu.ufcg.osindico.data.models.ServerResponse.AddressResponse;
import br.com.edu.ufcg.osindico.data.services.SyndicService;
import br.com.edu.ufcg.osindico.data.services.ZipCodeService;

public class RegisterCondoPresenterImpl implements RegisterCondoContract.Presenter,
        RegisterCondoContract.Model.OnRegisterCondoListener, RegisterCondoContract.Model.OnLoadAddressFinishedListener {

    private RegisterCondoContract.View view;
    private RegisterCondoContract.Model model;

    public RegisterCondoPresenterImpl(SyndicService service,ZipCodeService zipCodeService,
                                      RegisterCondoContract.View view) {
        this.model = new RegisterCondoModelImpl(service, zipCodeService);
        this.view = view;
    }

    @Override
    public void validateCondoCredentials(String name, String street, int number,
                                         String complement, String neighbor, String city,
                                         String zipCode, String state, Long syndicId) {
        if (view != null) {
            this.view.showProgress();

            this.model.register(name, street, number, complement, neighbor, city, zipCode,
                    state, syndicId, this);
        }
    }

    @Override
    public void getAddressByZipCode(String zipcode) {
        this.model.loadAddressByZipCode(zipcode, this);
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }

    @Override
    public void onNameError() {
        if (view != null) {
            view.hideProgress();
            view.setNameError();
        }
    }

    @Override
    public void onStreetError() {
        if (view != null) {
            view.hideProgress();
            view.setStreetError();
        }
    }

    @Override
    public void onNumberError() {
        if (view != null) {
            view.hideProgress();
            view.setNumberError();
        }
    }

    @Override
    public void onCityError() {
        if (view != null) {
            view.hideProgress();
            view.setCityError();
        }
    }

    @Override
    public void onZipCodeError() {
        if (view != null) {
            view.hideProgress();
            view.setZipCodeError();
        }
    }

    @Override
    public void onStateError() {
        if (view != null) {
            view.hideProgress();
            view.setStateError();
        }
    }

    @Override
    public void onNeighborError() {
        if (view != null) {
            view.hideProgress();
            view.setNeighborError();
        }
    }

    @Override
    public void onComplementError() {

    }

    @Override
    public void onServerError(String message) {
        if (view != null) {
            view.hideProgress();
            view.setServerError(message);
        }
    }

    @Override
    public void onSuccess() {
        if (view != null) {
            view.hideProgress();
            view.navigateToLogin();
        }
    }

    @Override
    public void onSuccessGetAddress(AddressResponse address) {
        if (view != null) {
            view.hideProgress();
            view.setAddressDataViews(address);
        }
    }
}
