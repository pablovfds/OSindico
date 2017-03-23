package br.com.edu.ufcg.osindico.data.services;

import java.security.Provider;
import java.util.List;

import br.com.edu.ufcg.osindico.data.models.DwellerDetails;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.MessageResponse;
import br.com.edu.ufcg.osindico.data.models.ServiceRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface DwellerApi {
    @POST("/api/register/dweller")
    Call<MessageResponse> registerDweller(@Body DwellerDetails request);

    @GET("/api/syndicate/message")
    Call<List<MessageResponse>> loadMessageRequests(@Header("Authorization") String authorization);

    @Headers({"Content-Type: application/json"})
    @POST("/api/dweller/service")
    Call<MessageResponse> sendServiceRequest(@Header("Authorization") String authorization,
                                      @Body ServiceRequest request);

}
