package br.com.edu.ufcg.osindico.data.models;

import com.google.gson.annotations.SerializedName;

public class Address {

    @SerializedName("cep")
    private String zipCode;

    @SerializedName("logradouro")
    private String street;

    @SerializedName("complemento")
    private String complement;

    @SerializedName("bairro")
    private String getNeighbor;

    @SerializedName("localidade")
    private String city;

    @SerializedName("uf")
    private String state;

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighbor() {
        return getNeighbor;
    }

    public void setNeighbor(String neighbor) {
        this.getNeighbor = neighbor;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
