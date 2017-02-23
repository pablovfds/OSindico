package br.com.edu.ufcg.osindico.data.models.ServerResponse;

import com.google.gson.annotations.SerializedName;

public class DwellerServerResponse {

    @SerializedName("message")
    private String message;

    public DwellerServerResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
