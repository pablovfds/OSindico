package br.com.edu.ufcg.osindico.data.models;

import com.google.gson.annotations.SerializedName;


public class MessageDetails {

    @SerializedName("message")
    private String message;

    public MessageDetails(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String newMessage) {
        this.message = newMessage;
    }
}
