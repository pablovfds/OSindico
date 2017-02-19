package br.com.edu.ufcg.osindico.registerCondo.mvp;

import br.com.edu.ufcg.osindico.data.Services.SyndicService;

public class RegisterCondoPresenterImpl implements RegisterCondoContract.Presenter,
        RegisterCondoContract.Model.OnRegisterCondoListener {

    private RegisterCondoContract.View view;
    private RegisterCondoContract.Model model;

    public RegisterCondoPresenterImpl(SyndicService service, RegisterCondoContract.View view) {
        this.model = new RegisterCondoModelImpl(service);
        this.view = view;
    }

    @Override
    public void validateCondoCredentials(String name, String phone, String address, int number,
                                         String city, String zipCode, String state, Long syndicId) {
        if (view != null) {
            this.view.showProgress();

            this.model.register(name, phone, address, number, city, zipCode, state, syndicId, this);
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
    public void onAddressError() {
        if (view != null) {
            view.hideProgress();
            view.setAddressError();
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
