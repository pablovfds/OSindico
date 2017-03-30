package br.com.edu.ufcg.osindico.data.services;

import java.util.List;

import br.com.edu.ufcg.osindico.data.models.CondoDetails;
import br.com.edu.ufcg.osindico.data.models.RuleDetails;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.MessageResponse;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.DwellerResponse;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.RuleResponse;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.ServiceRequestResponse;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.VisitorResponse;
import br.com.edu.ufcg.osindico.data.models.SyndicDetails;
import br.com.edu.ufcg.osindico.data.models.ServerResponse.SyndicServerResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface SyndicApi {
    @POST("/api/register/syndicate")
    Call<SyndicServerResponse> registerSyndic(@Body SyndicDetails request);

    @POST("/api/register/condominium")
    Call<MessageResponse> registerCondo(@Body CondoDetails request);

    @POST("/api/syndicate/rules")
    Call<RuleResponse> registerRegra(@Header("Authorization") String authorization,
                                     @Body RuleDetails ruleDetails);

    @GET("/api/syndicate/requests")
    Call<List<DwellerResponse>> loadRequestsResidents(@Header("Authorization") String authorization);

    @Headers({"Content-Type: application/json"})
    @POST("/api/syndicate/requests/{id}/{accept}")
    Call<MessageResponse> updateResidentStatus(@Header("Authorization") String authorization,
                                               @Path("id") Long id, @Path("accept") boolean accept);

    @Headers({"Content-Type: application/json"})
    @POST("/api/syndicate/message")
    Call<MessageResponse> sendMessage(@Header("Authorization") String authorization,
                                      @Body String request);

    @GET("/api/syndicate/service")
    Call<List<ServiceRequestResponse>> loadServiceRequestsResidents(
            @Header("Authorization") String authorization);

    @Headers({"Content-Type: application/json"})
    @PUT("/api/syndicate/service/{id}")
    Call<MessageResponse> updateServiceRequestStatus(@Header("Authorization") String authorization,
                                               @Path("id") Long id);

    @GET("/api/syndicate/service")
    Call<List<VisitorResponse>> loadDwellerVisitors(@Header("Authorization") String authorization);
}
