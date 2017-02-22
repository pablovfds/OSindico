package br.com.edu.ufcg.osindico.loginUser.mvp;

import br.com.edu.ufcg.osindico.data.models.ServerResponse.LoginResponse;
import br.com.edu.ufcg.osindico.data.services.LoginService;

public class LoginUserPresenterImpl implements LoginUserContract.Presenter, LoginUserContract.Model.OnLoginUserListener{
    private LoginUserContract.View loginUserView;
    private LoginUserContract.Model loginUserModel;


    public LoginUserPresenterImpl(LoginService loginService) {
        this.loginUserModel = new LoginUserModelImpl(loginService);
    }

    @Override
    public void validateCredentials(String email, String senha) {
        if(loginUserView != null){
            this.loginUserModel.loginUser(email, senha, this);
        }
    }

    @Override
    public void onEmailError() {
        if (loginUserView != null){
            this.loginUserView.setEmailError();
        }
    }

    @Override
    public void onPasswordError() {
        if (loginUserView != null){
            this.loginUserView.setPasswordError();
        }
    }

    @Override
    public void onSuccess(LoginResponse loginResponse) {
        if (loginUserView != null){
            this.loginUserView.setSuccessLogin(loginResponse);
        }
    }

    @Override
    public void onServerError(String message) {
        if (loginUserView != null){
            this.loginUserView.setServerError(message);
        }
    }


    @Override
    public void setView(LoginUserContract.View view) {
        this.loginUserView = view;
    }

    @Override
    public void onDestroy() {
        this.loginUserView = null;
    }
}
