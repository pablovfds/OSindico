package br.com.edu.ufcg.osindico.data.models;

import com.google.gson.annotations.SerializedName;

public class DwellerDetails {

    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("contact")
    private Contact contact;
    @SerializedName("condominiumId")
    private Long condominiumId;
    @SerializedName("fcmToken")
    private String fcmToken;

    public DwellerDetails() {

    }

    public DwellerDetails(String name, String email, String password, Contact contact, Long condominiumId, String fcmToken) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.contact = contact;
        this.condominiumId = condominiumId;
        this.fcmToken = fcmToken;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Long getCondominiumId() {
        return condominiumId;
    }

    public void setCondominiumId(Long condominiumId) {
        this.condominiumId = condominiumId;
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }
}
