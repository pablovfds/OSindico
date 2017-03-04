package br.com.edu.ufcg.osindico.data.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by emanoel on 04/03/17.
 */

public class RulesService {

    private static final String SERVER_URL = "https://sindico.herokuapp.com";
    private final RulesApi mRulesApi;
    private Retrofit retrofit;

    public RulesService() {
        retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mRulesApi = retrofit.create(RulesApi.class);
    }

    public RulesApi getmRulesApi() {
        return mRulesApi;
    }
}
