package br.com.edu.ufcg.osindico.data.models;

import com.google.gson.annotations.SerializedName;

public class Address {

    @SerializedName("zipCode")
    private String zipCode;

    @SerializedName("street")
    private String street;

    @SerializedName("complement")
    private String complement;

    @SerializedName("neighbor")
    private String neighbor;

    @SerializedName("city")
    private String city;

    @SerializedName("state")
    private String state;

    @SerializedName("number")
    private int number;

    public Address(String zipCode, String street, String complement, String neighbor, String city,
                   String state, int number) {
        this.zipCode = zipCode;
        this.street = street;
        this.complement = complement;
        this.neighbor = neighbor;
        this.city = city;
        this.state = state;
        this.number = number;
    }

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
        return neighbor;
    }

    public void setNeighbor(String neighbor) {
        this.neighbor = neighbor;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
