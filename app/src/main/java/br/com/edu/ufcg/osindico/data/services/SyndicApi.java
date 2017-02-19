package br.com.edu.ufcg.osindico.data.Services;

import br.com.edu.ufcg.osindico.data.models.CondoDetails;
import br.com.edu.ufcg.osindico.data.models.CondoServerResponse;
import br.com.edu.ufcg.osindico.data.models.SyndicDetails;
import br.com.edu.ufcg.osindico.data.models.SyndicServerResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SyndicApi {
    @POST("/api/register/syndicate")
    Call<SyndicServerResponse> registerSyndic(@Body SyndicDetails request);

    @POST("/api/register/condominium")
    Call<CondoServerResponse> registerCondo(@Body CondoDetails request);
}
