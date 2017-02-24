package br.com.edu.ufcg.osindico.data.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Contact implements Serializable{

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
