package br.com.edu.ufcg.osindico.registerCondo.mvp;

import br.com.edu.ufcg.osindico.data.models.Address;
import br.com.edu.ufcg.osindico.data.models.CondoDetails;
import br.com.edu.ufcg.osindico.data.services.SyndicService;

public class RegisterCondoPresenterImpl implements RegisterCondoMVPContract.RegisterCondoPresenter,
        RegisterCondoMVPContract.RegisterCondoModel.OnValidateCondoFinishedListener {

    private RegisterCondoMVPContract.RegisterCondoView view;
    private RegisterCondoMVPContract.RegisterCondoModel model;

    public RegisterCondoPresenterImpl(SyndicService service) {
        this.model = new RegisterCondoModelImpl(service);
    }

    @Override
    public void validateCondoCredentials(String name, String phone, String street, int number,
                                    String neighborhood, String city, String zipCode, String state,
                                    String country) {

        if (view != null) {
            this.view.showProgress();

            Address address = new Address(street, number, neighborhood, city, zipCode, state, country);
            CondoDetails condoDetails = new CondoDetails(name, phone, address);

            this.model.validateCredentialsCondo(condoDetails, this);
        }

    }

    @Override
    public void setView(RegisterCondoMVPContract.RegisterCondoView view) {
        if (view != null) {
            this.view = view;
        }
    }


    @Override
    public void onDestroy() {
        this.view = null;
    }

    @Override
    public void onNameError() {

    }

    @Override
    public void onPhoneError() {

    }

    @Override
    public void onStreetError() {

    }

    @Override
    public void onNumberError() {

    }

    @Override
    public void onNeighborhoodError() {

    }

    @Override
    public void onCityError() {

    }

    @Override
    public void onZipCodeError() {

    }

    @Override
    public void onStateError() {

    }

    @Override
    public void onCountyError() {

    }

    @Override
    public void onSuccess() {

    }
}
