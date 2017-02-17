package br.com.edu.ufcg.osindico.data.models;

public class Address {

    private final String address;
    private int number;
    private String city;
    private String zipCode;
    private String state;

    public Address(String address, int number, String city, String zipCode, String state) {
        this.address = address;
        this.number = number;
        this.city = city;
        this.zipCode = zipCode;
        this.state = state;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }
}
