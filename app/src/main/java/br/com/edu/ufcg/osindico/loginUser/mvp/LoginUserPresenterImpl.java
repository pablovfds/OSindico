package br.com.edu.ufcg.osindico.loginUser.mvp;

import br.com.edu.ufcg.osindico.data.services.LoginService;

public class LoginUserPresenterImpl implements LoginUserContract.Presenter,
        LoginUserContract.Model.OnLoginUserListener{

    private LoginUserContract.View view;
    private LoginUserContract.Model model;

    public LoginUserPresenterImpl(LoginService service, LoginUserContract.View view) {
        this.view = view;
    }

    @Override
    public void onEmailError() {

    }

    @Override
    public void onPasswordError() {

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onServerError(String message) {

    }

    @Override
    public void validateCredentials(String email, String senha) {

    }

    @Override
    public void setView(LoginUserContract.View view) {

    }

    @Override
    public void onDestroy() {

    }
}
