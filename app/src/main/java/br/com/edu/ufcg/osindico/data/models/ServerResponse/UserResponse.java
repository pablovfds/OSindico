package br.com.edu.ufcg.osindico.data.models.ServerResponse;

import com.google.gson.annotations.SerializedName;

public class UserResponse {
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("tipo")
    private String tipo;
    @SerializedName("ativo")
    private Boolean ativo;

    public UserResponse() {
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
