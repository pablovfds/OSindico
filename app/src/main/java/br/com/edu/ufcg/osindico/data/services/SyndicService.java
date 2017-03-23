package br.com.edu.ufcg.osindico.data.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SyndicService {

    private static final String SERVER_URL = "https://sindico.herokuapp.com";
    private SyndicApi mSyndicApi;
    private Retrofit retrofit;

    public SyndicService() {
        if (mSyndicApi == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(SERVER_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            mSyndicApi = retrofit.create(SyndicApi.class);
        }
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public SyndicApi getSyndicApi() {
        return mSyndicApi;
    }
}
