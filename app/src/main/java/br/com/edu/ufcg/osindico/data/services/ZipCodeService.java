package br.com.edu.ufcg.osindico.data.services;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ZipCodeService {
    private static final String ZIP_CODE_API = "https://viacep.com.br";

    private ZipCodeApi mZipCodeApi;

    public ZipCodeService() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ZIP_CODE_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mZipCodeApi = retrofit.create(ZipCodeApi.class);
    }

    public ZipCodeApi getZipCodeApi() {
        return mZipCodeApi;
    }
}
