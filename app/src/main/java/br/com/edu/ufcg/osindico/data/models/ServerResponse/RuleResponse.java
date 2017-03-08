package br.com.edu.ufcg.osindico.data.models.ServerResponse;

import com.google.gson.annotations.SerializedName;

/**
 * Created by emanoel on 07/03/17.
 */

public class RuleResponse {

    @SerializedName("message")
    private String message;

    public RuleResponse() {

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
