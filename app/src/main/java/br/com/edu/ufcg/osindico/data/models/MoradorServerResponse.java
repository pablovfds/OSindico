package br.com.edu.ufcg.osindico.data.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by emanoel on 19/02/17.
 */
public class MoradorServerResponse {

    @SerializedName("message")
    private String mensagem;

    public MoradorServerResponse() {}

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
