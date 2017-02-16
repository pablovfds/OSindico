package br.com.edu.ufcg.osindico.data.services;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

public class SyndicService {
    private static final String SERVER_URL = "http://jsonplaceholder.typicode.com";
    private SyndicApi mSyndicApi;

    public SyndicService() {
        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("Accept", "application/json");
            }
        };

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(SERVER_URL)
                .setRequestInterceptor(requestInterceptor)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        mSyndicApi = restAdapter.create(SyndicApi.class);
    }

    public SyndicApi getSyndicApi() {
        return mSyndicApi;
    }
}
