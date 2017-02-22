package br.com.edu.ufcg.osindico.loginUser.mvp;

import com.google.gson.Gson;

import java.io.IOException;

import br.com.edu.ufcg.osindico.Utils.FormValidate;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.LoginResponse;
import br.com.edu.ufcg.osindico.data.models.UserLoginDetails;
import br.com.edu.ufcg.osindico.data.services.LoginService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginUserModelImpl implements LoginUserContract.Model{
    private LoginService mLoginService;

    public LoginUserModelImpl(LoginService mLoginService) {
        this.mLoginService = mLoginService;
    }

    @Override
    public void loginUser(String email, String senha, final OnLoginUserListener listener) {
        boolean error = false;

        if(!FormValidate.isValidEmail(email)){
            listener.onEmailError();
            error = true;
        }

        if (!FormValidate.isValidPassword(senha)){
            listener.onPasswordError();
            error = true;
        }

        if(!error){
            UserLoginDetails userLoginDetails = new UserLoginDetails(email,senha);

            final Call<LoginResponse> mService = mLoginService.getLoginApi().loginUser(userLoginDetails);

            mService.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    Gson gson = new Gson();

                    if (response.isSuccessful()){
                        listener.onSuccess(response.body());
                    } else {
                        LoginResponse errorResponse =
                                null;
                        try {
                            errorResponse = gson.fromJson(response.errorBody().string(), LoginResponse.class);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        listener.onServerError(errorResponse.getMessage());
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    call.cancel();
                    listener.onServerError(t.getMessage());
                }
            });
        }
    }
}
