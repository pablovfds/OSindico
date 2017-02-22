package br.com.edu.ufcg.osindico.data.models.ServerResponse;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("token")
    private String token;
    @SerializedName("message")
    private String message;
    @SerializedName("usuario")
    private UserResponse usuario;

    public LoginResponse() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserResponse getUsuario() {
        return usuario;
    }

    public void setUsuario(UserResponse usuario) {
        this.usuario = usuario;
    }
}