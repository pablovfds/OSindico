package br.com.edu.ufcg.osindico.data.services;

import br.com.edu.ufcg.osindico.data.models.DwellerDetails;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.MessageResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface DwellerApi {
    @POST("/api/register/dweller")
    Call<MessageResponse> registerDweller(@Body DwellerDetails request);
}
