package br.com.edu.ufcg.osindico.registerSyndic.mvp;

import br.com.edu.ufcg.osindico.base.BaseView;
import br.com.edu.ufcg.osindico.data.services.SyndicService;

public class RegisterSyndicPresenterImpl implements RegisterSyndicContract.Presenter,
        RegisterSyndicContract.Model.OnRegisterSyndicListener {

    private RegisterSyndicContract.View registerSyndicView;
    private RegisterSyndicContract.Model registerSyndicModel;

    public RegisterSyndicPresenterImpl(SyndicService service) {
        this.registerSyndicModel = new RegisterSyndicModelImpl(service);
    }

    @Override
    public void validateCredentials(String name, String email, String password,
                                    String confirmPassword, String phone) {
        if (registerSyndicView != null){
            this.registerSyndicView.showProgress();

            this.registerSyndicModel.registerSyndic(name, email, password, confirmPassword,
                    phone, this);
        }
    }

    @Override
    public void setView(BaseView view) {
        this.registerSyndicView = (RegisterSyndicContract.View) view;
    }

    @Override
    public void onDestroy() {
        this.registerSyndicView = null;
    }

    @Override
    public void onNameError() {
        if (registerSyndicView != null){
            this.registerSyndicView.hideProgress();
            this.registerSyndicView.setNameError();
        }
    }

    @Override
    public void onEmailError() {
        if (registerSyndicView != null){
            this.registerSyndicView.hideProgress();
            this.registerSyndicView.setEmailError();
        }
    }

    @Override
    public void onPhoneError() {
        if (registerSyndicView != null){
            this.registerSyndicView.hideProgress();
            this.registerSyndicView.setPhoneError();
        }
    }

    @Override
    public void onPasswordError() {
        if (registerSyndicView != null){
            this.registerSyndicView.hideProgress();
            this.registerSyndicView.setPasswordError();
        }
    }

    @Override
    public void onConfirmPasswordError() {
        if (registerSyndicView != null){
            this.registerSyndicView.hideProgress();
            this.registerSyndicView.setConfirmPasswordError();
        }
    }

    @Override
    public void onSuccess(Long syndicId) {
        if (registerSyndicView != null){
            this.registerSyndicView.hideProgress();
            this.registerSyndicView.navigateToRegisterCondo(syndicId);
        }
    }

    @Override
    public void onServerError(String errorMessage) {
        if (registerSyndicView != null){
            this.registerSyndicView.hideProgress();
            this.registerSyndicView.setServerError(errorMessage);
        }
    }
}
