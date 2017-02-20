package br.com.edu.ufcg.osindico.data.services;


import br.com.edu.ufcg.osindico.data.models.Address;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ZipCodeApi {
    @GET("/ws/{zipCode}/json/")
    Call<Address> getAddressByZipCode(@Path("zipCode") String zipCode);
}
