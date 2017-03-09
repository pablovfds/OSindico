package br.com.edu.ufcg.osindico.data.models.exception;

import com.google.gson.annotations.SerializedName;

import br.com.edu.ufcg.osindico.data.models.ServerResponse.MessageResponse;

public class SpringException extends MessageResponse{


    @SerializedName("status")
    private int status;

    public SpringException() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "status: " + getStatus() + " msg: " + getMessage();
    }
}
