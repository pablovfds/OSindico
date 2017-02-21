package br.com.edu.ufcg.osindico.data.models;

import com.google.gson.annotations.SerializedName;

public class Contact {

    @SerializedName("phoneNumber")
    private String phoneNumber;

    public Contact(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
