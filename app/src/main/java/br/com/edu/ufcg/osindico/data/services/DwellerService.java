package br.com.edu.ufcg.osindico.data.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DwellerService {

    private static final String SERVER_URL = "https://sindico.herokuapp.com";
    private final DwellerApi mDwellerApi;

    public DwellerService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mDwellerApi = retrofit.create(DwellerApi.class);
    }

    public DwellerApi getDwellerApi() {
        return mDwellerApi;
    }
}
