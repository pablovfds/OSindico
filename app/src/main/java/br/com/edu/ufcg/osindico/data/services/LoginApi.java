package br.com.edu.ufcg.osindico.data.services;


import br.com.edu.ufcg.osindico.data.models.ServerResponse.LoginResponse;
import br.com.edu.ufcg.osindico.data.models.UserLoginDetails;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginApi {
    @POST("/api/auth")
    Call<LoginResponse> loginUser(@Body UserLoginDetails userLoginDetails);
}
