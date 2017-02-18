package br.com.edu.ufcg.osindico.registerCondo.mvp;

import br.com.edu.ufcg.osindico.data.models.Address;
import br.com.edu.ufcg.osindico.data.models.CondoDetails;
import br.com.edu.ufcg.osindico.data.models.Contact;
import br.com.edu.ufcg.osindico.data.services.SyndicService;

public class RegisterCondoPresenterImpl implements RegisterCondoContract.Presenter,
        RegisterCondoContract.Model.OnValidateCondoListener, RegisterCondoContract.Model.OnRegisterCondoListener {

    private RegisterCondoContract.View view;
    private RegisterCondoContract.Model model;

    public RegisterCondoPresenterImpl(SyndicService service, RegisterCondoContract.View view) {
        this.model = new RegisterCondoModelImpl(service);
        this.view = view;
    }

    @Override
    public void validateCondoCredentials(String name, String phone, String address, int number,
                                         String city, String zipCode, String state) {

        if (view != null) {
            this.view.showProgress();

            Address addressItem = new Address(address, number, city, zipCode, state);
            Contact contact = new Contact(phone);
            CondoDetails condoDetails = new CondoDetails(name, contact, addressItem);

            this.model.validateCredentialsCondo(condoDetails, this);
        }

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
    public void onPhoneError() {
        if (view != null) {
            view.hideProgress();
            view.setPhoneError();
        }
    }

    @Override
    public void onAddressError() {

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
    public void onSuccessValidation(CondoDetails condoDetails) {
        if (view != null) {
            view.navigateToRegisterSyndic(condoDetails);
        }
    }

    @Override
    public void onServerError() {
        if (view != null) {
            view.hideProgress();
            view.setServerError();
        }
    }

    @Override
    public void onSuccess() {
        if (view != null) {
            view.hideProgress();
            view.navigateToLogin();
        }
    }
}
