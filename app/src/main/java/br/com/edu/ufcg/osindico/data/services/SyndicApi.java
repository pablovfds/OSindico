package br.com.edu.ufcg.osindico.data.services;

import br.com.edu.ufcg.osindico.data.models.CondoServerRequest;
import br.com.edu.ufcg.osindico.data.models.CondoServerResponse;
import br.com.edu.ufcg.osindico.data.models.SyndicServerRequest;
import br.com.edu.ufcg.osindico.data.models.SyndicServerResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SyndicApi {
    @POST("/api/register/syndicate")
    Call<SyndicServerResponse> registerSyndic(@Body SyndicServerRequest request);

    @POST("/api/register/condominium")
    Call<CondoServerResponse> registerCondo(@Body CondoServerRequest request);
}
