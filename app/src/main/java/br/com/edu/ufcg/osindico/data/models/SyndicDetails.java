package br.com.edu.ufcg.osindico.data.models;

import com.google.gson.annotations.SerializedName;

public class SyndicDetails {

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    @SerializedName("contact")
    private Contact contact;
    @SerializedName("fcmToken")
    private String fcmToken;

    public SyndicDetails(String name, String email, String password, Contact contact,String fcmToken) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.contact = contact;
        this.fcmToken = fcmToken;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String newEmail) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String newPassword) {
        this.password = password;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }
}
