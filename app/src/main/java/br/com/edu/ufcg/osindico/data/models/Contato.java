package br.com.edu.ufcg.osindico.data.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by emanoel on 20/02/17.
 */

public class Contato {

    @SerializedName("phoneNumber")
    private String telefone;

    public Contato(String telefone) {
        this.telefone = telefone;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
