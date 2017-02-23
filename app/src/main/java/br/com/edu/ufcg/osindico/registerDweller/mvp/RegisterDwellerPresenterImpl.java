package br.com.edu.ufcg.osindico.registerDweller.mvp;

import br.com.edu.ufcg.osindico.data.models.Contact;
import br.com.edu.ufcg.osindico.data.services.DwellerService;

public class RegisterDwellerPresenterImpl implements RegisterDwellerContract.Presenter, RegisterDwellerContract.Model.OnRegisterDwellerListener {

    private RegisterDwellerContract.View view;
    private RegisterDwellerContract.Model model;

    public RegisterDwellerPresenterImpl(DwellerService service, RegisterDwellerContract.View view) {
        this.model = new RegisterDwellerModelImpl(service);
        this.view = view;
    }

    @Override
    public void validateCredentials(String name, Contact contact, String email, String password, String confirmPassword, Long condominiumId) {
        if (view != null) {
            this.model.registerDweller(name, contact, email, password, confirmPassword, condominiumId, this);
        }
    }

    @Override
    public void setView(RegisterDwellerContract.View view) {
        this.view = view;
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }

    @Override
    public void onNameError() {
        if (view != null) {
            view.setNameError();
        }
    }

    @Override
    public void onEmailError() {
        if (view != null) {
            view.setEmailError();
        }
    }

    @Override
    public void onPasswordError() {
        if (view != null) {
            view.setPasswordError();
        }
    }

    @Override
    public void onConfirmPasswordError() {
        if (view != null) {
            view.setConfirmPasswordError();
        }
    }

    @Override
    public void onPhoneNumberError() {
        if (view != null) {
            view.setPhoneNumberError();
        }
    }

    @Override
    public void onSuccess(String message) {
        if (view != null) {
            view.setSuccess(message);
        }
    }

    @Override
    public void onServerError(String serverError) {
        if (view != null) {
            view.setServerError(serverError);
        }
    }
}
