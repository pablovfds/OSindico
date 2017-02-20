package br.com.edu.ufcg.osindico.data.models.exception;

import com.google.gson.annotations.SerializedName;

public class SpringException {

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private int status;

    public SpringException() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "status: " + status + " msg: " + message;
    }
}