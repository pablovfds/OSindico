package br.com.edu.ufcg.osindico.data.models.ServerResponse;

import com.google.gson.annotations.SerializedName;

import br.com.edu.ufcg.osindico.data.models.exception.SpringException;

public class SyndicServerResponse {

    @SerializedName("sindicoId")
    private Long id;

    @SerializedName("springException")
    private SpringException springException;

    public SyndicServerResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SpringException getSpringException() {
        return springException;
    }

    public void setSpringException(SpringException springException) {
        this.springException = springException;
    }

    @Override
    public String toString() {
        return "sindico: " + id + " Spring: " + springException.toString();
    }
}
