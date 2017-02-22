package br.com.edu.ufcg.osindico.data.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by emanoel on 19/02/17.
 */
public class MoradorServerResponse {

    @SerializedName("message")
    private String message;

    public MoradorServerResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
