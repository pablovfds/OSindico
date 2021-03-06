package br.com.edu.ufcg.osindico.data.services;

import java.util.List;

import br.com.edu.ufcg.osindico.data.models.ServerResponse.RuleResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface RulesApi {
    @GET("/api/syndicate/rules")
    Call<List<RuleResponse>> getCondominiumRules(@Header("Authorization") String authorization);
}
