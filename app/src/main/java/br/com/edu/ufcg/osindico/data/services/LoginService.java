package br.com.edu.ufcg.osindico.data.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginService {
    private static final String SERVER_URL = "https://sindico.herokuapp.com";
    private LoginApi mLoginApi;

    public LoginService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mLoginApi = retrofit.create(LoginApi.class);
    }

    public LoginApi getLoginApi() {
        return mLoginApi;
    }
}
