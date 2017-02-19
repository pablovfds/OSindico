package br.com.edu.ufcg.osindico.data.services;

import br.com.edu.ufcg.osindico.data.models.DadosMorador;
import br.com.edu.ufcg.osindico.data.models.MoradorServerResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by emanoel on 19/02/17.
 */

public interface MoradorApi {
    @POST("/api/register/morador")
    Call<MoradorServerResponse> cadastraMorador(@Body DadosMorador request);
}
