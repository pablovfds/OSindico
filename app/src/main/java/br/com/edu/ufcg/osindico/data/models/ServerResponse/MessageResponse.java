package br.com.edu.ufcg.osindico.data.models.ServerResponse;

import com.google.gson.annotations.SerializedName;


public class MessageResponse  {

    @SerializedName("id")
    private Long id;
    @SerializedName("message")
    private String message;

    public MessageResponse() {}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
