package br.com.edu.ufcg.osindico.data.services;

import java.util.List;

import br.com.edu.ufcg.osindico.data.models.CondoDetails;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.CondoServerResponse;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.ResidentResponse;
import br.com.edu.ufcg.osindico.data.models.SyndicDetails;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.SyndicServerResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface SyndicApi {
    @POST("/api/register/syndicate")
    Call<SyndicServerResponse> registerSyndic(@Body SyndicDetails request);

    @POST("/api/register/condominium")
    Call<CondoServerResponse> registerCondo(@Body CondoDetails request);

    @GET("/api/syndicate/requests")
    Call<List<ResidentResponse>> loadRequestsResidents(@Header("Authorization") String authorization);
}
