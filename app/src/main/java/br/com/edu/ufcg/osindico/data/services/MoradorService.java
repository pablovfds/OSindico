package br.com.edu.ufcg.osindico.data.services;

import br.com.edu.ufcg.osindico.data.models.DadosMorador;
import br.com.edu.ufcg.osindico.data.models.MoradorServerResponse;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;

/**
 * Created by emanoel on 19/02/17.
 */

public class MoradorService {

    private static final String SERVER_URL = "https://sindico.herokuapp.com";
    private final MoradorApi mMoradorApi;

    public MoradorService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mMoradorApi = retrofit.create(MoradorApi.class);
    }

    public MoradorApi getMoradorApi() {
        return mMoradorApi;
    }
}
