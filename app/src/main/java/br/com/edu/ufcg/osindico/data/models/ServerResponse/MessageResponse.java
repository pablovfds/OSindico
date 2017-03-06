package br.com.edu.ufcg.osindico.data.models.ServerResponse;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Cathy on 06/03/2017.
 */

public class MessageResponse {

    @SerializedName("message")
    private String message;
    @SerializedName("date")
    private String date;

    public MessageResponse() {}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
