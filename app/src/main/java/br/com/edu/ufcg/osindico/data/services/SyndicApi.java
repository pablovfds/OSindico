package br.com.edu.ufcg.osindico.data.services;

import br.com.edu.ufcg.osindico.data.models.CondoDetails;
import br.com.edu.ufcg.osindico.data.models.SyndicDetails;
import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.POST;

public interface SyndicApi {
    @POST("/posts")
    public Callback<SyndicDetails> registerSyndic(SyndicDetails syndicDetails,
                                                  CondoDetails condoDetails, Callback<Response> responseCallback);
}
