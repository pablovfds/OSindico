package br.com.edu.ufcg.osindico.data.models;

public class CondoDetails {

    private String name;
    private Address address;
    private String syndicId;

    public CondoDetails(String name, Address address, String syndicId) {
        this.name = name;
        this.address = address;
        this.syndicId = syndicId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getSyndicId() {
        return syndicId;
    }

    public void setSyndicId(String syndicId) {
        this.syndicId = syndicId;
    }
}
