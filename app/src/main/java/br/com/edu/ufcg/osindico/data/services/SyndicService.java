package br.com.edu.ufcg.osindico.data.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SyndicService {
    private static final String SERVER_URL = "http://jsonplaceholder.typicode.com";
    private SyndicApi mSyndicApi;

    public SyndicService() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mSyndicApi = retrofit.create(SyndicApi.class);
    }

    public SyndicApi getSyndicApi() {
        return mSyndicApi;
    }
}
