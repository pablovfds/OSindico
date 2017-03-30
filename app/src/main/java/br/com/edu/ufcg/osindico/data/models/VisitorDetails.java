package br.com.edu.ufcg.osindico.data.models;

import com.google.gson.annotations.SerializedName;

public class VisitorDetails {

    @SerializedName("name")
    private String name;

    @SerializedName("cpf")
    private String cpf;

    @SerializedName("getvisitDay")
    private String getvisitDay;

    @SerializedName("id")
    private Long id;

    public VisitorDetails(String name, String cpf, String getvisitDay, Long id) {
        this.name = name;
        this.cpf = cpf;
        this.getvisitDay = getvisitDay;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getGetvisitDay() {
        return getvisitDay;
    }

    public void setGetvisitDay(String getvisitDay) {
        this.getvisitDay = getvisitDay;
    }
}
